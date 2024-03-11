package org.seydaliev.applicationprocessingsystem.controller;

import org.junit.jupiter.api.Test;
import org.seydaliev.applicationprocessingsystem.dto.ApplicationDto;
import org.seydaliev.applicationprocessingsystem.dto.UserDto;
import org.seydaliev.applicationprocessingsystem.service.AdminService;
import org.seydaliev.applicationprocessingsystem.service.UserService;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetUsers() throws Exception {
        Page<UserDto> userDtoPage = new PageImpl<>(Collections.singletonList(new UserDto()), PageRequest.of(0, 1), 1);
        when(userService.getUsersforPage(any())).thenReturn(userDtoPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetApplications() throws Exception {
        Page<ApplicationDto> applicationDtoPage = new PageImpl<>(Collections.singletonList(new ApplicationDto()), PageRequest.of(0, 1), 1);
        when(adminService.getApplicationsByStatus(any(), any())).thenReturn(applicationDtoPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/applications")
                        .param("status", "pending")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAssignOperatorRole() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        when(userService.assignOperatorRole(any())).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/admin/users/1/roles/operator")
                        .with(csrf())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1L));
    }

}
