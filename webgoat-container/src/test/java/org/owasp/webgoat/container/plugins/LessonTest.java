package org.owasp.webgoat.container.plugins;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.owasp.webgoat.container.WebGoat;
import org.owasp.webgoat.container.i18n.Language;
import org.owasp.webgoat.container.i18n.PluginMessages;
import org.owasp.webgoat.container.session.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.function.Function;

import static org.mockito.Mockito.when;

/**
 * @author nbaars
 * @since 5/20/17.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WebGoat.class)
@TestPropertySource(locations = {"classpath:/application-webgoat.properties", "classpath:/application-webgoat-test.properties"})
public abstract class LessonTest {

    @LocalServerPort
    protected int localPort;
    protected MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    protected PluginMessages messages;
    @Autowired
    private Function<String, Flyway> flywayLessons;
    @MockBean
    protected WebSession webSession;
    @MockBean
    private Language language;
    @Value("${webgoat.user.directory}")
    protected String webGoatHomeDirectory;

    @BeforeEach
    void init() {
        when(webSession.getUserName()).thenReturn("unit-test");
        when(language.getLocale()).thenReturn(Locale.getDefault());
    }

    @PostConstruct
    public void createFlywayLessonTables() {
        flywayLessons.apply("PUBLIC").migrate();
    }
}
