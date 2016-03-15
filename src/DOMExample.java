import java.io.IOException;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * This program uses the DOM style parser built into Java.
 * The relevant classes are:
 * Document which represents the XML document parsed into a tree
 * DocumentBuilder parses an XML document and creates a Document instance for you
 * DocumentBuilderFactory which is a class for instantiating DocumentBuilders on our behalf
 * NodeList is a class that represents a list of DOM tree nodes - not necessarily in any kind of order
 * NodeList uses the familar List API
 * Node is a class that represents one Node or element in the DOM tree
 * note: once again we don't use new to instantiate any of these classes
 * (this is fairly common in frameworks)
 */
public class DOMExample {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//first step is to get the DocumentBuilderFactory
		//use a static method call (we use the class name instead of a reference to an instance)
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		//get a DocumentBuilder from the factory
		//the factory will deal with the complexity of properly initializing the class
		DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
		
		//allow the user to choose a file...
		//as an exercise you may implement this in its own function that returns a string
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		//pass the selected file to the loadSequenceFromFile method
		String filename = fc.getSelectedFile().getAbsolutePath();
		
		
		//parse the input xml file to create a Document
		Document document = docBuilder.parse(filename);
		//now document will contain the entire XML document as a tree structure that we can traverse..
		//we can traverse this document in any arbitrary way we want
		//and we can edit the document as well.
		
		//Document.getDocumentElement() gets us the root Node (root element)
		//we can get the name of each Node (element) by calling Node.getNodeName()
		System.out.println("Root element of " + filename + " is: " + document.getDocumentElement().getNodeName());
		
		//search the tree for particular elements by using the Document.getElementsByTagName(String name)
		NodeList myNodeList = document.getElementsByTagName("URL");
		//iterate through the NodeList using a loop and the familiar List Interface
		for(int i =0; i < myNodeList.getLength(); i++){
			//get a node by index by calling NodeList.item(int index)
			Node myNode = myNodeList.item(i);
			//use the Node.getTextContent() method to retrieve the text from the element
			System.out.println(myNode.getTextContent());
			
		}
		
	}

}



