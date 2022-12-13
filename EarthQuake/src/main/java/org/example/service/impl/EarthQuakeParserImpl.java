package org.example.service.impl;

import org.example.DAO.QuakeEntry;
import org.example.service.intf.EarthQuakeParser;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class EarthQuakeParserImpl implements EarthQuakeParser {
    private DocumentBuilder builder;
    private Document document;
    private NodeList nodeList;

    public EarthQuakeParserImpl() {
        // TODO Auto-generated constructor stub
    }

    private void initializeParser(){
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("parser configuration exception");
        }
    }

    private void parseToNodeList(String source){
        try {
            if (source.startsWith("http")){
                document = builder.parse(source);
            }
            else {
                document = builder.parse(new File(source));
            }
            nodeList = document.getDocumentElement().getChildNodes();
        } catch (SAXException se){
            System.err.println("sax exception");
        }
        catch (IOException ioe){
            System.err.println("ioexception");
        }
    }
    @Override
    public ArrayList<QuakeEntry> read(String source) {
        initializeParser();
        parseToNodeList(source);
        ArrayList<QuakeEntry> list = new ArrayList<>();

        for(int k=0; k < nodeList.getLength(); k++){
            Node node = nodeList.item(k);

            if (node.getNodeName().equals("entry")) {
                Element elem = (Element) node;
                NodeList t1 = elem.getElementsByTagName("georss:point");
                NodeList t2 = elem.getElementsByTagName("title");
                NodeList t3 = elem.getElementsByTagName("georss:elev");
                double lat = 0.0, lon = 0.0, depth = 0.0;
                String title = "NO INFORMATION";
                double mag = 0.0;

                if (t1 != null) {
                    String s2 = t1.item(0).getChildNodes().item(0).getNodeValue();
                    //System.out.print("point2: "+s2);
                    String[] args = s2.split(" ");
                    lat = Double.parseDouble(args[0]);
                    lon = Double.parseDouble(args[1]);
                }
                if (t2 != null){
                    String s2 = t2.item(0).getChildNodes().item(0).getNodeValue();

                    String mags = s2.substring(2,5);
                    if (mags.contains("?")) {
                        mag = 0.0;
                        System.err.println("unknown magnitude in data");
                    }
                    else {
                        mag = Double.parseDouble(mags);
                    }
                    int sp = s2.indexOf(" ",5);
                    title = s2.substring(sp+1);
                    if (title.startsWith("-")){
                        int pos = title.indexOf(" ");
                        title = title.substring(pos+1);
                    }
                }
                if (t2 != null){
                    String s2 = t3.item(0).getChildNodes().item(0).getNodeValue();
                    depth = Double.parseDouble(s2);
                }
                QuakeEntry loc = new QuakeEntry(lat,lon,mag,title,depth);
                list.add(loc);
            }

        }
        return list;
    }
}
