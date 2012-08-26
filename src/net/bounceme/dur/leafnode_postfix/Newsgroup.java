package net.bounceme.dur.leafnode_postfix;

public class Newsgroup {

    private String newsgroup;

    public Newsgroup(String newsgroup) {
        this.newsgroup = newsgroup;
    }

    public Newsgroup(Page page) {
        newsgroup = page.getFolderFullName();
    }

    public String getNewsgroup() {
        return newsgroup;
    }

    public void setNewsgroup(String newsgroup) {
        this.newsgroup = newsgroup;
    }
}
