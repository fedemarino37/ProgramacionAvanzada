package practicaTurnoMañana;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class testGenericos {

	@SuppressWarnings("deprecation")
	@Test
	public void test() {
		Soldado s1 = new Soldado(3, 3);
		Soldado s2 = new Soldado(3, 4);
		Assert.assertEquals(100, s2.getEnergia());
		Assert.assertEquals(200, s2.getSalud());
		s1.atacar(s2);
		Assert.assertEquals(90, s1.getEnergia());
		Assert.assertEquals(190, s2.getSalud());
		s1.recibir(new PocionAgua());
		Assert.assertEquals(100, s2.getEnergia());
	}

	@Test
	public void probandoCaballeroConSoldado() {
		Soldado s1 = new Soldado(3, 3);
		Caballero c1 = new Caballero(3, 4);
		
		Assert.assertEquals(200, s1.getSalud());
		c1.atacar(s1);
		c1.atacar(s1);
		c1.atacar(s1);
		c1.atacar(s1); //no deberia atacar por caballo revelde
		Assert.assertEquals(50, s1.getSalud());
		c1.recibir(new PocionAgua());
		c1.atacar(s1);
		Assert.assertEquals(0, s1.getSalud());
	}
}
