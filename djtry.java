package project6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;




class Vertex{
	
	public String Vertexname;
	public Vertex(String name){
		Vertexname=name;
	}
	
	ArrayList<Edge> edges=new ArrayList<Edge>();
	
	public boolean known=false;
	public int dist=Integer.MAX_VALUE;
	public Vertex path;
	public String getVertexName() {
		// TODO Auto-generated method stub
		return Vertexname;
	}
	
	 public String toString(){
		return Vertexname+" "+ dist+"  "+ path+" "+known;
	}
}

class Edge{
	
	public String destination;
	public String weight;
	
	public Edge(String destination,String weight){
		
		this.destination=destination;
		this.weight=weight;
		
	}




}

public class djtry {
	
	
	public static Vertex getVertex(String name, Vertex[] vertices)
	{
		
		if(name!=null && !name.trim().equalsIgnoreCase(""))
		for(int i=0; i< vertices.length; i++)
		{
			if(vertices[i].getVertexName().equalsIgnoreCase(name))
			{
				return vertices[i];
			}
		}
		return null;
	}
	


public static void Shortestpath(Vertex source,Vertex[] vertices){
	

	source.dist=0;
	
	
	
	while(true){
		
		Vertex u = getMinimumCost(vertices);
	//	System.out.println(u);
		if(u.Vertexname=="default"){
			break;
		}
		
		u.known=true;
		
		for(Edge e: u.edges){
			
			Vertex v=getVertex(e.destination,vertices);
			int cost=Integer.parseInt(e.weight);
			
			int distance = u.dist + cost;
            if (distance < v.dist && !v.known) {
            
            	v.dist=distance;
            	v.path=u;
            
            	
            }
			
			
		}
		
	}
	
}
	
	
	




public static Vertex getMinimumCost(Vertex[] vertices)
{
	
	//System.out.println(vertices[0]);
	Vertex temp = new Vertex("default");
	for (int i=0; i<vertices.length; i++) {
		//System.out.println(vertices[i]);
		//System.out.println(temp.dist);
		if(temp.dist > vertices[i].dist && !vertices[i].known)
		{
			//System.out.println("hello");
			temp = vertices[i];
		}
		
	}
	//System.out.println("1"+temp);
	return temp;
}
	
public static List<Vertex> printShortestPath(Vertex v)
{
    List<Vertex> path = new ArrayList<Vertex>();
    for (Vertex vertex = v; vertex != null; vertex = vertex.path)
        path.add(vertex);
    Collections.reverse(path);
    return path;
}	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String Choice="n";
		do{
		Vertex[] vertices = readAirportInfoFile();
		System.out.println("Enter departure airport: ");
		Scanner sc = new Scanner(System.in);
		String source = sc.nextLine();
		System.out.println("Enter arrival airport: ");
		String destination = sc.nextLine();
		//String source="ATL";
		//String destination="HOU";
		
		Vertex start = getVertex(source, vertices);
		Vertex end = getVertex(destination, vertices);
		
		if(start!=null && end != null)
		{
			Shortestpath(start, vertices);
			
			end = getVertex(destination, vertices);
			    System.out.println("Price: " + end.dist);
			    List<Vertex> path = printShortestPath(end);
			    System.out.println("Connections: "+(path.size()-2));
			    System.out.println("Route: ");
			    for (Vertex vertex : path) {
			    	System.out.print("--> " + vertex.getVertexName());	
				}
			    
		}   
		else
		{
			System.out.println("Wrong Airport Name!! Try again!!");
		}
		
		System.out.println("\nDo you want to continue? ");
		Choice = sc.nextLine();
		
		}while(Choice.equalsIgnoreCase("y"));
		
		

	}


	 public static Vertex[] readAirportInfoFile()
	    {
	    	ArrayList<Vertex> listOfVertices = new ArrayList<Vertex>();
	    	ArrayList<Edge> edges = new ArrayList<Edge>();
	    	
	    	File input = new File("C:/Users/jeswaniyogesh/Desktop/airports.txt");
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(input));
				String p = "";
				while ((p = br.readLine()) != null) {
					String[] values = p.split("\\s{2,}");
					// source = values[0];
					edges = new ArrayList<Edge>();
					Vertex ver = new Vertex("");
					Vertex dest = null;
					ver=new Vertex(values[0]);
					for (int i = 1; i < values.length; i++) {
						String[] destArray = values[i].split("\\s");
						Edge edge=new Edge(destArray[0],destArray[1]);
						edges.add(edge);
					
					}
					
					
					ver.edges=edges;
					listOfVertices.add(ver);
				}

			} catch (Exception e) {
				e.printStackTrace();;
			}
	    	
			return (Vertex[])listOfVertices.toArray(new Vertex[listOfVertices.size()]);
	
	
	    }
	}
	
	

