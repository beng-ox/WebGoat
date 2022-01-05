package org.owasp.webgoat.container.service;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.owasp.webgoat.container.assignments.LessonTrackerInterceptor;
import org.owasp.webgoat.container.lessons.LessonScanner;
import org.owasp.webgoat.container.users.UserService;
import org.owasp.webgoat.container.users.UserTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.owasp.webgoat.container.service.LabelService.URL_LABELS_MVC;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ************************************************************************************************
 * This file is part of WebGoat, an Open Web Application Security Project utility. For details,
 * please see http://www.owasp.org/
 * <p>
 * Copyright (c) 2002 - 2014 Bruce Mayhew
 * <p>
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 * <p>
 * Getting Source ==============
 * <p>
 * Source for this application is maintained at https://github.com/WebGoat/WebGoat, a repository for free software
 * projects.
 * <p>
 *
 * @author nbaars
 * @version $Id: $Id
 * @since November 29, 2016
 */
@WebMvcTest(value = {LabelService.class})
@ActiveProfiles({"test", "webgoat"})
class LabelServiceTest {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserTrackerRepository userTrackerRepository;
    @MockBean
    private LessonTrackerInterceptor lessonTrackerInterceptor;
    @MockBean
    private LessonScanner lessonScanner;

    @Test
    @WithMockUser(username = "guest", password = "guest")
    void withoutLocale() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL_LABELS_MVC))
                .andExpect(status().isOk())
                .andExpect(jsonPath("password", CoreMatchers.is("Password")));
    }

}
