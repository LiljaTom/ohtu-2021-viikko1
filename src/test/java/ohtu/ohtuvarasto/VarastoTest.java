package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudenVarastonTilavuusEiVoiOllaNegatiivinen() {
        Varasto epakelpo = new Varasto(-0.1);

        assertEquals(0, epakelpo.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitetullaKonstruktorillaOikeaTilavuus() {
        Varasto v = new Varasto(10, 5);

        assertEquals(10, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktoriEiHyvaksyNegatiivistaTilavuutta() {
        Varasto v = new Varasto(-0.1, 5);

        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitetullaKonstruktorillaOikeaSaldo() {
        Varasto v = new Varasto(10, 5);

        assertEquals(5, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktoriEiHyvaksyNegatiivistaSaldoa() {
        Varasto v = new Varasto(10, -0.1);

        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudenVarastonSaldoEiVoiYlittaaTilavuutta() {
        Varasto v = new Varasto(10, 11);

        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiLisataNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(-0.1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoonEiVoiLisataYliTilavuuden() {
        varasto.lisaaVarastoon(10.1);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    } 

    @Test
    public void varastostaEiVoiOttaaNegatiivista() {
        varasto.otaVarastosta(-0.1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void yliSaldonOttaminenPalauttaaKaikenMahdollisen() {
        varasto.lisaaVarastoon(5);
        double maara = varasto.otaVarastosta(6);
        assertEquals(5, maara, vertailuTarkkuus);
    }

    @Test
    public void merkkijonoEsitysToimii() {
        varasto.lisaaVarastoon(4.5);
        String esitys = "saldo = 4.5, vielä tilaa 5.5";
        esitys = "";

        assertEquals(esitys, varasto.toString());
    }

}