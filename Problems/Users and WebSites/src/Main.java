abstract class BaseEntity {
    public abstract long getId();

    public abstract void setId(long id) ;

    public abstract long getVersion() ;

    public abstract void setVersion(long version) ;

    public abstract String getName() ;

    public abstract void setName(String name) ;
}

class User extends BaseEntity {

    protected long id;

    protected long version;

    protected String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Visit extends User {

    protected long id;

    protected long version;

    protected User user;

    protected WebSite site;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WebSite getSite() {
        return site;
    }

    public void setSite(WebSite site) {
        this.site = site;
    }
}

class WebSite extends User {

    protected long id;

    protected long version;

    protected String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}