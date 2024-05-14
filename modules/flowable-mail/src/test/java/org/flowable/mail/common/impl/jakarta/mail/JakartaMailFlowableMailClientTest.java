package org.flowable.mail.common.impl.jakarta.mail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.mail.common.api.MailMessage;
import org.flowable.mail.common.api.SendMailRequest;
import org.flowable.mail.common.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class JakartaMailFlowableMailClientTest {

    private Session session;
    private MimeMessage mimeMessage;
    private BaseMailHostServerConfiguration mailServerConfiguration;
    private JakartaMailFlowableMailClient flowableMailClient;
    private String defaultFrom = "testDefaultFrom@test.com";
    private String forceTo = "testForceTo@test.com";

    @BeforeEach
    public void setup() {
        session = Session.getDefaultInstance(new Properties());
        mimeMessage = new MimeMessage(session);
        mailServerConfiguration = new BaseMailHostServerConfiguration();
        MailDefaultsConfiguration mailDefaultsConfiguration = new MailDefaultsConfigurationImpl(defaultFrom, Charset.defaultCharset(), List.of());
        flowableMailClient = new JakartaMailFlowableMailClient(mailServerConfiguration, mailDefaultsConfiguration);
    }

    @Test
    public void addToTest_WithoutForceTo() throws MessagingException {
        List<String> to = List.of("test1@test.com", "test@2test.com");
        flowableMailClient.addTo(mimeMessage, to);

        List<InternetAddress> addresses = Arrays.stream((InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.TO)).toList();
        assertThat(addresses.stream().filter(it -> to.contains(it.getAddress())).toList()).isNotEmpty();
    }

    @Test
    public void addToTest_WithForceTo() throws MessagingException {
        List<String> to = List.of("test1@test.com", "test@2test.com");
        flowableMailClient = new JakartaMailFlowableMailClient(mailServerConfiguration, new MailDefaultsConfigurationImpl(defaultFrom, Charset.defaultCharset(), List.of(forceTo)));
        flowableMailClient.addTo(mimeMessage, to);

        List<InternetAddress> addresses = Arrays.stream((InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.TO)).toList();
        assertThat(addresses.stream().filter(it -> forceTo.contains(it.getAddress())).toList()).isNotEmpty();
    }

    @Test
    public void addCcTest() throws MessagingException {
        List<String> cc = List.of("test1@test.com", "test@2test.com");
        flowableMailClient.addCc(mimeMessage, cc);

        List<InternetAddress> addresses = Arrays.stream((InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.CC)).toList();
        assertThat(addresses.stream().filter(it -> cc.contains(it.getAddress())).toList()).isNotEmpty();
    }

    @Test
    public void addBccTest() throws MessagingException {
        List<String> bcc = List.of("test1@test.com", "test@2test.com");
        flowableMailClient.addBcc(mimeMessage, bcc);

        List<InternetAddress> addresses = Arrays.stream((InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.BCC)).toList();
        assertThat(addresses.stream().filter(it -> bcc.contains(it.getAddress())).toList()).isNotEmpty();
    }


    @Test
    public void setFrom() throws MessagingException {
        String from = "test1@test.com";
        flowableMailClient.setFrom(mimeMessage, from);
        List<InternetAddress> addresses = Arrays.stream((InternetAddress[]) mimeMessage.getFrom()).toList();
        assertThat(addresses.stream().filter(it -> from.equals(it.getAddress()))).isNotEmpty();
    }

    @Test
    public void testCreateInternetAddress_ValidEmail() {
        String validEmail = "user@example.com";
        assertDoesNotThrow(() -> {
            InternetAddress address = flowableMailClient.createInternetAddress(validEmail);
            assertEquals(validEmail, address.getAddress());
        });
    }

    @Test
    public void testCreateInternetAddress_InvalidEmail() {
        String invalidEmail = "userexample.com";
        assertThrows(FlowableMailException.class, () -> {
            flowableMailClient.createInternetAddress(invalidEmail);
        });
    }

    @Test
    public void testCreateInternetAddress_InternationalDomain() {
        String internationalEmail = "user@例え.テスト";
        assertDoesNotThrow(() -> {
            InternetAddress address = flowableMailClient.createInternetAddress(internationalEmail);
            assertNotNull(address);
            assertTrue(address.getAddress().contains("@xn--"));
        });
    }

    @Test
    public void testCreateSession() {
        ((BaseMailHostServerConfiguration) flowableMailClient.serverConfiguration).setHost("localhost");
        assertDoesNotThrow(() -> {
            Session session = flowableMailClient.createSession();
        });
    }

    @Test
    public void testCreateSession_noSMTPHost() {
        assertThrows(FlowableException.class, () -> {
            Session session = flowableMailClient.createSession();
        });
    }

    @Test
    public void testCreateSession_InvalidServerConfiguration() {
        flowableMailClient = new JakartaMailFlowableMailClient(null, flowableMailClient.defaultsConfiguration);
        assertThrows(FlowableException.class, () -> {
            Session session = flowableMailClient.createSession();
        });
    }

    @Test
    public void prepareRequest(){
        ((BaseMailHostServerConfiguration) flowableMailClient.serverConfiguration).setHost("localhost");
        assertDoesNotThrow(() -> {
            MailMessage message = new MailMessage();
            message.setPlainContent("Dear XY!");
            SendMailRequest sendMailRequest = new SendMailRequest(message);
            flowableMailClient.prepareRequest(sendMailRequest);
        });

    }
}
