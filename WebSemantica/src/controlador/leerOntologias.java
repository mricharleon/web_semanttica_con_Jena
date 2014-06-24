/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author Edgar
 */
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class leerOntologias {

    public String leerClasesySub(String ruta) {
        String xys = "";
        OntModel model = ModelFactory.createOntologyModel();
        OntModel a = ModelFactory.createOntologyModel();
        OntModel b = ModelFactory.createOntologyModel();
        try {
            InputStream file = new FileInputStream(ruta);
            b.read(file, "");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Archivo no compatible...\n"
                    + "los archivos deben ser de extensión wxml");
        }
        Iterator<DatatypeProperty> propiedades = a.listDatatypeProperties();
        Iterator<OntClass> clases = b.listClasses();
        while (clases.hasNext()) {
            if (clases.next().getLocalName() != null) {
                xys = xys.concat(String.valueOf(clases.next().getLocalName()).concat("\n"));
            }
        }
        return xys;
    }
///metodo para devolver individuos

    public String devolverIndividos(String ruta) throws FileNotFoundException {
        OntModel ontologia = ModelFactory.createOntologyModel();
        String i="";
        InputStream file = new FileInputStream(ruta);
        ontologia.read(file, "");
        ExtendedIterator iteratorClasses = ontologia.listClasses();
        while (iteratorClasses.hasNext()) {
            OntClass ontClass = (OntClass) iteratorClasses.next();
            Iterator<Individual> individuos = ontologia.listIndividuals();
            while (individuos.hasNext()) {
                i=i.concat(String.valueOf(individuos.next().getLocalName()).concat("\n"));
                
            }  
            break;
        }
        return i;
    }
    
    //metodo para obtener las propiedades
    public String leerP(String ruta) {
        OntModel areaOntology = ModelFactory.createOntologyModel();
        String relaciones="";
        try {
            InputStream file = new FileInputStream(ruta);
            areaOntology.read(file, "");
        } catch (FileNotFoundException ex) {
        }
        Iterator<ObjectProperty> relacion = areaOntology.listObjectProperties();
        while (relacion.hasNext()) {
            relaciones=relaciones.concat(String.valueOf(relacion.next().getLocalName()).concat("\n"));
        }
        return relaciones;
    }

}
