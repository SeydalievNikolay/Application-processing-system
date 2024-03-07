package org.seydaliev.applicationprocessingsystem.controller;

import org.junit.jupiter.api.Test;
import org.seydaliev.applicationprocessingsystem.dto.ApplicationDto;
import org.seydaliev.applicationprocessingsystem.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class OperatorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService applicationService;

    @Test
    @WithMockUser(roles = "OPERATOR")
    public void testGetApplications() throws Exception {
        Page<ApplicationDto> applicationDtoPage = new PageImpl<>(Collections.singletonList(new ApplicationDto()), PageRequest.of(0, 1), 1);
        when(applicationService.getApplicationsForOperator(any(), any())).thenReturn(applicationDtoPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/operator/applications")
                        .param("name", "John Doe")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1));
    }

    @Test
    @WithMockUser(roles = "OPERATOR")
    public void testGetApplicationById() throws Exception {
        ApplicationDto applicationDto = new ApplicationDto();
        when(applicationService.getApplicationById(any())).thenReturn(applicationDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/operator/applications/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @WithMockUser(roles = "OPERATOR")
    public void testAcceptApplication() throws Exception {
        ApplicationDto applicationDto = new ApplicationDto();
        when(applicationService.acceptApplication(any())).thenReturn(applicationDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/operator/applications/1/accept")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @WithMockUser(roles = "OPERATOR")
    public void testRejectApplication() throws Exception {
        ApplicationDto applicationDto = new ApplicationDto();
        when(applicationService.rejectApplication(any())).thenReturn(applicationDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/operator/applications/1/reject")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }
}
