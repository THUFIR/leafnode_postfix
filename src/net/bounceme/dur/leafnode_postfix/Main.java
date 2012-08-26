package net.bounceme.dur.leafnode_postfix;

import static java.lang.System.out;
import java.util.List;
import javax.mail.Folder;

public class Main {

    private Usenet u = Usenet.INSTANCE;

    public static void main(String[] args) {
        Main m = new Main();
    }

    private Main() {
        List<Folder> folders = u.getFolders();
        out.println(folders);
    }
}
