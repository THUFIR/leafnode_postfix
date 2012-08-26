package net.bounceme.dur.leafnode_postfix;

import java.io.IOException;
import static java.lang.System.out;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailClient extends Authenticator {

    public static final int SHOW_MESSAGES = 1;
    public static final int CLEAR_MESSAGES = 2;
    public static final int SHOW_AND_CLEAR =
            SHOW_MESSAGES + CLEAR_MESSAGES;
    protected String from;
    protected Session session;
    protected PasswordAuthentication authentication;

    public MailClient(UserHost userHost) {
        String user = userHost.getUser();
        String host = userHost.getHost();
        boolean debug = userHost.isDebug();
        from = user + '@' + host;
        authentication = new PasswordAuthentication(user, user);
        Properties props = new Properties();
        props.put("mail.user", user);
        props.put("mail.host", host);
        props.put("mail.debug", debug ? "true" : "false");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        session = Session.getDefaultInstance(props);
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return authentication;
    }

    public void sendMessage(Message m) throws MessagingException, IOException {
        Message msg = new MimeMessage(session);
        InternetAddress address = new InternetAddress("thufir@dur");
        msg.setRecipient(Message.RecipientType.TO, address);
        msg.setSubject(m.getSubject());
        //msg.setText(m.getContent().toString());
        Multipart mp = new MimeMultipart();
        BodyPart part = new MimeBodyPart();
        //part.setHeader("Content-Type", "text/html");
        part.setContent(m.getContent(), "text/html");
        mp.addBodyPart(part);
        msg.setContent(mp);
        out.println(m.getRecipients(Message.RecipientType.TO));
        Transport.send(msg);
    }

    public void checkInbox(int mode)
            throws MessagingException, IOException {
        if (mode == 0) {
            return;
        }
        boolean show = (mode & SHOW_MESSAGES) > 0;
        boolean clear = (mode & CLEAR_MESSAGES) > 0;
        String action =
                (show ? "Show" : "")
                + (show && clear ? " and " : "")
                + (clear ? "Clear" : "");
        System.out.println(action + " INBOX for " + from);
        Store store = session.getStore();
        store.connect();
        Folder root = store.getDefaultFolder();
        Folder inbox = root.getFolder("inbox");
        inbox.open(Folder.READ_WRITE);
        Message[] msgs = inbox.getMessages();
        if (msgs.length == 0 && show) {
            System.out.println("No messages in inbox");
        }
        for (int i = 0; i < msgs.length; i++) {
            MimeMessage msg = (MimeMessage) msgs[i];
            if (show) {
                System.out.println("    From: " + msg.getFrom()[0]);
                System.out.println(" Subject: " + msg.getSubject());
                System.out.println(" Content: " + msg.getContent());
            }
            if (clear) {
                msg.setFlag(Flags.Flag.DELETED, true);
            }
        }
        inbox.close(true);
        store.close();
        System.out.println();
    }
}