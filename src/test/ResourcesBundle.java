package test;

import static org.junit.Assert.assertEquals;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class ResourcesBundle {

	
	@Test
	public void loadResourceBundle() {
				
		//default locale es_ES
		ResourceBundle  rb_es = ResourceBundle.getBundle("test.i18nmessages");				
		assertEquals("No existe key='test' con value='prueba' ", rb_es.getString("test") , "prueba" );
		assertEquals("No existe key='title' con value='titulo' ", rb_es.getString("title") , "titulo" );
		
		//euskaraz
		ResourceBundle  rb_eu = ResourceBundle.getBundle("test.i18nmessages", new Locale("eu_ES") );				
		assertEquals("No existe key='test' con value='froga' ", rb_eu.getString("test") , "froga" );
		assertEquals("No existe key='title' con value='izenburua' ", rb_eu.getString("title") , "izenburua" );
		
		//english
		ResourceBundle  rb_en = ResourceBundle.getBundle("test.i18nmessages", new Locale("en_EN") );				
		assertEquals("No existe key='test' con value='froga' ", rb_en.getString("test") , "test" );
		assertEquals("No existe key='title' con value='izenburua' ", rb_en.getString("title") , "title" );
		
		
	}
	
}
