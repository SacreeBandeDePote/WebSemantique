import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ExtractHTML {

   public static void main(String[] args) {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

      try {
         DocumentBuilder builder = factory.newDocumentBuilder();
         File fileXML = new File("xml.xml");
         Document xml = builder.parse(fileXML);
         
         ArrayList<Element> list = new ArrayList<>();
         Element root = xml.getDocumentElement();
         getNodes(root, list);
         System.out.println(list);
         for(Element el : list){
            System.out.println("Nom : " + el.getNodeName() + " - Valeur : " + el.getTextContent());
         }         
      } catch (ParserConfigurationException e) {
         e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   
   public static void getNodes(Node n, ArrayList<Element> listElement){
      String str = new String();
      if(n instanceof Element){
         Element element = (Element)n;
         if(n.getNodeName().equals("feuille"))
            listElement.add(element);
       
         //Nous allons maintenant traiter les n�uds enfants du n�ud en cours de traitement
         int nbChild = n.getChildNodes().getLength();
         //Nous r�cup�rons la liste des n�uds enfants
         NodeList list = n.getChildNodes();
        
         //nous parcourons la liste des n�uds
         for(int i = 0; i < nbChild; i++){
            Node n2 = list.item(i);
            
            //si le n�ud enfant est un Element, nous le traitons
            if (n2 instanceof Element){
               //appel r�cursif � la m�thode pour le traitement du n�ud et de ses enfants 
               getNodes(n2, listElement);
            }
         }
      }
   }   
}