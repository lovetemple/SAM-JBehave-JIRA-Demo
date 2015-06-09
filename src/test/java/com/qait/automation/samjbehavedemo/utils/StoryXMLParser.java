package com.qait.automation.samjbehavedemo.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.qait.automation.samjbehavedemo.getstory.Constants;

/**
 *
 * @author prashantshukla
 */
public class StoryXMLParser {

	private final String xml_loc;
	private NodeList nodelist;

	public StoryXMLParser(String xmlFileName) {
		if (xmlFileName.toLowerCase().trim().endsWith(".xml")) {
			this.xml_loc = Constants.STORY_XML_LOC + xmlFileName;
		} else {
			this.xml_loc = Constants.STORY_XML_LOC + xmlFileName + ".xml";
		}

		this.nodelist = getStoryXMLDoc();
	}

	private NodeList getStoryXMLDoc() {
		Document doc = null;
		try {
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			doc = docBuilder.parse(FileHandler.getFile(this.xml_loc));
			doc.getDocumentElement().normalize();
		} catch (ParserConfigurationException | SAXException | IOException ex) {
			ex.printStackTrace();
			Logger.getLogger(StoryXMLParser.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return (NodeList) doc;
	}

	public Map<String, Integer> getScenarioResult(Node scenarioNode) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		int counterPass = 0, counterFail = 0, counterSkip = 0, counterPending = 0;
		NodeList stepNodeList = getNodeListByTagname(scenarioNode, "step");
		for (int i = 0; i < stepNodeList.getLength(); i++) {
			String outcome = getAttributeValue(stepNodeList.item(i), "outcome");
			if (outcome.equalsIgnoreCase("successful")) {
				counterPass++;
			}
			if (outcome.equalsIgnoreCase("failed")) {
				counterFail++;
			}
			if (outcome.equals("pending")) {
				counterPending++;

			}
			if (outcome.equalsIgnoreCase("notPerformed")) {
				counterSkip++;
			}
		}
		result.put(TestStates.PASS, counterPass);
		result.put(TestStates.FAIL, counterFail);
		result.put(TestStates.PENDING, counterPending);
		result.put(TestStates.NOTPERFORMED, counterSkip);
		return result;
	}

	public Element getScenario(int scenarioIndex) {
		NodeList scenarioList = ((Element) getStoryXMLDoc().item(0))
				.getElementsByTagName("scenario");
		return (Element) scenarioList.item(scenarioIndex);
	}

	public int getScenarioCount() {
		return ((Element) this.nodelist.item(0)).getElementsByTagName(
				"scenario").getLength();
	}

	public Map<String, String> getStepResults(Node scenarioNode) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		NodeList stepNodeList = getNodeListByTagname(scenarioNode, "step");
		for (int i = 0; i < stepNodeList.getLength(); i++) {
			String outcome = getAttributeValue(stepNodeList.item(i), "outcome");
			result.put("Step " + (i + 1), stepNodeList.item(i).getTextContent()
					+ " - " + outcome.toUpperCase());
		}
		return result;
	}

	private static NodeList getNodeListByTagname(Node node, String tagname) {
		return ((Element) node).getElementsByTagName(tagname);
	}

	private static String getAttributeValue(Node node, String attribute) {
		return node.getAttributes().getNamedItem(attribute).getNodeValue();
	}

}
