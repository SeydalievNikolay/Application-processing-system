package org.seydaliev.applicationprocessingsystem.controller;

import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService applicationService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testCreateApplication() throws Exception {
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setId(1L);
        applicationDto.setStatus("принято");
        applicationDto.setText("Test application");
        applicationDto.setPhoneNumber("1234567890");
        applicationDto.setName("Test Application");
        applicationDto.setCreatedAt(LocalDateTime.now());
        when(applicationService.createApplication(any())).thenReturn(applicationDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/applications/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\": \"принято\", \"text\": \"Test application text\", \"phoneNumber\": \"1234567890\", \"name\": \"Test Application\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.status").value("принято"))
                .andExpect(jsonPath("$.text").value("Test application"))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$.name").value("Test Application"))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetApplications() throws Exception {
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setId(1L);
        applicationDto.setStatus("принято");
        applicationDto.setText("Test application text");
        applicationDto.setPhoneNumber("1234567890");
        applicationDto.setName("Test Application");
        applicationDto.setCreatedAt(LocalDateTime.now());
        Page<ApplicationDto> applicationDtoPage = new PageImpl<>(Collections.singletonList(applicationDto), PageRequest.of(0, 1), 1);
        when(applicationService.getApplication(any(), any())).thenReturn(applicationDtoPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/applications")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].status").value("принято"))
                .andExpect(jsonPath("$.content[0].text").value("Test application text"))
                .andExpect(jsonPath("$.content[0].phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$.content[0].name").value("Test Application"))
                .andExpect(jsonPath("$.content[0].createdAt").exists());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetApplicationById() throws Exception {
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setId(1L);
        applicationDto.setStatus("принято");
        applicationDto.setText("Test application text");
        applicationDto.setPhoneNumber("1234567890");
        applicationDto.setName("Test Application");
        applicationDto.setCreatedAt(LocalDateTime.now());
        when(applicationService.getApplicationById(any())).thenReturn(applicationDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/applications/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.status").value("принято"))
                .andExpect(jsonPath("$.text").value("Test application text"))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$.name").value("Test Application"))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @WithMockUser(roles = "OPERATOR")
    public void testAcceptApplication() throws Exception {
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setId(1L);
        applicationDto.setStatus("принято");
        applicationDto.setText("Test application text");
        applicationDto.setPhoneNumber("1234567890");
        applicationDto.setName("Test Application");
        applicationDto.setCreatedAt(LocalDateTime.now());
        when(applicationService.acceptApplication(any())).thenReturn(applicationDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/applications/1/accept")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.status").value("принято"))
                .andExpect(jsonPath("$.text").value("Test application text"))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$.name").value("Test Application"))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @WithMockUser(roles = "OPERATOR")
    public void testRejectApplication() throws Exception {
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setId(1L);
        applicationDto.setStatus("отклонено");
        applicationDto.setText("Test application text");
        applicationDto.setPhoneNumber("1234567890");
        applicationDto.setName("Test Application");
        applicationDto.setCreatedAt(LocalDateTime.now());
        when(applicationService.rejectApplication(any())).thenReturn(applicationDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/applications/1/reject")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.status").value("отклонено"))
                .andExpect(jsonPath("$.text").value("Test application text"))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"))
                .andExpect(jsonPath("$.name").value("Test Application"))
                .andExpect(jsonPath("$.createdAt").exists());
    }
}
