package io.inaam.main.dto;

public class ClientDto
{
    private String client_name;
    private String client_secret;
    private String realm_id;

    public String getRealm_id() {
        return realm_id;
    }

    public void setRealm_id(String realm_id) {
        this.realm_id = realm_id;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getClient_name() {
        return client_name;
    }

    public String getClient_secret() {
        return client_secret;
    }
}
