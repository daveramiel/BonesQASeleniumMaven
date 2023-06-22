package ar.globallogic.qa.webdriver;

import org.openqa.selenium.support.ui.WebDriverWait;

public class Configuracion {

    private String appUrl;
    private boolean verNavegador;
    private boolean navegadorPrivado;
    private int implicitlyWait;
    private int pageLoadTimeOut;
    private String browserName;
    private String usuario;
    private String password;

    public Configuracion() {

    }

    public Configuracion(String appUrl, boolean verNavegador, boolean navegadorPrivado,
                         int implicitlyWait, int pageLoadTimeOut, String browserName,
                         String usuario, String password) {
        this.appUrl = appUrl;
        this.verNavegador = verNavegador;
        this.navegadorPrivado = navegadorPrivado;
        this.implicitlyWait = implicitlyWait;
        this.pageLoadTimeOut = pageLoadTimeOut;
        this.browserName = browserName;
        this.usuario = usuario;
        this.password = password;
    }

    public String getBrowserName() {
        if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("firefox")) {
            return browserName;
        } else throw new IllegalArgumentException("No se encontro la propiedad browserName en el archivo");
    }

    public WebDriverWait getWebDriverWait() {
        return new WebDriverWait(WebDriver.getInstance(), getImplicitlyWait());
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public boolean isVerNavegador() {
        return verNavegador;
    }

    public void setVerNavegador(boolean verNavegador) {
        this.verNavegador = verNavegador;
    }

    public boolean isNavegadorPrivado() {
        return navegadorPrivado;
    }

    public void setNavegadorPrivado(boolean navegadorPrivado) {
        this.navegadorPrivado = navegadorPrivado;
    }

    public int getImplicitlyWait() {
        return implicitlyWait;
    }

    public void setImplicitlyWait(int implicitlyWait) {
        this.implicitlyWait = implicitlyWait;
    }

    public int getPageLoadTimeOut() {
        return pageLoadTimeOut;
    }

    public void setPageLoadTimeOut(int pageLoadTimeOut) {
        this.pageLoadTimeOut = pageLoadTimeOut;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
