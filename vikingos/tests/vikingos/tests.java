package vikingos;

import static org.junit.Assert.*;

import org.junit.Test;
import vikingo.Vikingo;

public class tests {

	@Test
	public void normalABerserker() {
		Vikingo vik = new Vikingo();
		vik.recibirDaño(30);
		assertEquals("Berserker", vik.getEstado().toString());
		assertEquals(1, vik.getDefensa());
		assertEquals(300, vik.getFuerza());
	}
	
	@Test
	public void berserkerANormal() {
		Vikingo vik = new Vikingo();
		vik.recibirDaño(30);
		assertEquals("Berserker", vik.getEstado().toString());
		
		vik.calmarse();
		assertEquals("Berserker", vik.getEstado().toString());
		vik.calmarse();
		assertEquals("Berserker", vik.getEstado().toString());
		vik.calmarse();
		assertEquals("Normal", vik.getEstado().toString());
		assertEquals(200, vik.getDefensa());
		assertEquals(100, vik.getFuerza());
	}
	
	@Test
	public void normalAMonje() {
		Vikingo vik = new Vikingo();
		vik.calmarse();
		assertEquals("Monje", vik.getEstado().toString());
		assertEquals(1000, vik.getDefensa());
		assertEquals(1, vik.getFuerza());
	}
	
	@Test
	public void MonjeANormal() {
		Vikingo vik = new Vikingo();
		vik.calmarse();
		assertEquals("Monje", vik.getEstado().toString());
		
		vik.recibirDaño(5);
		assertEquals("Normal", vik.getEstado().toString());
	}

}
