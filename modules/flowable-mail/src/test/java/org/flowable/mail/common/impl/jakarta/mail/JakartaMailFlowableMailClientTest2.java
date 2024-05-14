package org.flowable.mail.common.impl.jakarta.mail;

import jakarta.activation.DataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.flowable.mail.common.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JakartaMailFlowableMailClientTest2 {

    @Test
    public void testToASCIIEmail_withValidEmail() {
        MailServerConfiguration serverConfiguration = null;
        MailDefaultsConfiguration defaultsConfiguration = null;
        // Arrange
        JakartaMailFlowableMailClient jakartaMailFlowableMailClient = new JakartaMailFlowableMailClient(serverConfiguration, defaultsConfiguration);
        String email = "example@example.com";

        // Act
        String asciiEmail = jakartaMailFlowableMailClient.toASCIIEmail(email);

        // Assert
        assertEquals("example@example.com", asciiEmail);
    }

    @Test
    public void testToASCIIEmail_withInvalidEmail() {
        // Arrange
        MailServerConfiguration serverConfiguration = null;
        MailDefaultsConfiguration defaultsConfiguration = null;
        // Arrange
        JakartaMailFlowableMailClient jakartaMailFlowableMailClient = new JakartaMailFlowableMailClient(serverConfiguration, defaultsConfiguration);
        String email = "invalid-email";

        // Act
        String asciiEmail = jakartaMailFlowableMailClient.toASCIIEmail(email);

        // Assert
        assertEquals("invalid-email", asciiEmail);
    }

    private MimeMessage mimeMessage;
    private MailDefaultsConfiguration defaultsConfiguration;
    private MailServerConfiguration serverConfiguration;

    @BeforeEach
    public void setUp() {
        mimeMessage = new MimeMessage((Session) null);
        defaultsConfiguration = null;
        serverConfiguration = null;
    }

    @Test
    public void testSetFrom_withNonNullFrom() throws MessagingException {
        // Arrange
        JakartaMailFlowableMailClient jakartaMailFlowableMailClient = new JakartaMailFlowableMailClient(serverConfiguration, defaultsConfiguration);
        String from = "example@example.com";

        // Act
        jakartaMailFlowableMailClient.setFrom(mimeMessage, from);

        // Assert
        InternetAddress[] addresses = (InternetAddress[]) mimeMessage.getFrom();
        assertEquals(1, addresses.length);
        assertEquals(from, addresses[0].getAddress());
    }

    @Test
    public void testCreateMultiPartContent() throws MessagingException {
        // Arrange
        JakartaMailFlowableMailClient jakartaMailFlowableMailClient = new JakartaMailFlowableMailClient(serverConfiguration, defaultsConfiguration);
        String text = "Test text";
        String html = "<html><body><h1>Test HTML</h1></body></html>";
        String charset = "UTF-8";
        Collection<DataSource> attachments = null; // or provide sample attachments if needed

        // Act
        MimeMultipart result = jakartaMailFlowableMailClient.createMultiPartContent(text, html, charset, attachments);

        // Assert
        assertNotNull(result);

    }
    @Test
    public void testCreateMultiPartContent3() throws MessagingException {
        // Arrange
        JakartaMailFlowableMailClient jakartaMailFlowableMailClient = new JakartaMailFlowableMailClient(serverConfiguration, defaultsConfiguration);
        String text = "Test text";
        String html = "<html><body><h1>Test HTML</h1></body></html>";
        String charset = "UTF-8";
        Collection<DataSource> attachments = null; // or provide sample attachments if needed

        // Act
        MimeMultipart result = jakartaMailFlowableMailClient.createMultiPartContent(text, html, charset, attachments);

        // Assert
        assertEquals(2, result.getCount()); // Expecting two parts (text and html)


    }
    @Test
    public void testGetAuthenticator_withUserAndPassword1() {
        // Arrange
        MailHostServerConfiguration serverConfiguration = new BaseMailHostServerConfiguration();
        JakartaMailFlowableMailClient jakartaMailFlowableMailClient = new JakartaMailFlowableMailClient(serverConfiguration, defaultsConfiguration);

        // Act
        Authenticator authenticator = jakartaMailFlowableMailClient.getAuthenticator(serverConfiguration);

        // Assert

        String user = "testUser";
        String password = "testPassword";
        PasswordAuthentication passwordAuthentication = new PasswordAuthentication(user, password);
        assertNotNull(passwordAuthentication);
        assertEquals("testUser", passwordAuthentication.getUserName());
        assertEquals("testPassword", new String(passwordAuthentication.getPassword()));
    }


    @Test
    public void testGetAuthenticator_withUserAndPassword() {
        // Arrange
        MailHostServerConfiguration serverConfiguration = new BaseMailHostServerConfiguration();
        JakartaMailFlowableMailClient jakartaMailFlowableMailClient = new JakartaMailFlowableMailClient(serverConfiguration, defaultsConfiguration);

        // Act
        Authenticator authenticator = jakartaMailFlowableMailClient.getAuthenticator(serverConfiguration);

        // Assert

        String user = "testUser";
        String password = "testPassword";
        PasswordAuthentication passwordAuthentication = new PasswordAuthentication(user, password);
        assertNotNull(passwordAuthentication);
        assertEquals("testUser", passwordAuthentication.getUserName());
        assertEquals("testPassword", new String(passwordAuthentication.getPassword()));
    }
}