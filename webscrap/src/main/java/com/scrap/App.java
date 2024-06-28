package com.scrap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;


public class App
{
    public static String getData(String country) throws Exception{
        StringBuffer br=new StringBuffer();
        br.append("<html>");
        //appending country name in capital
        br.append(country.toUpperCase()+"<br>");
        String url="https://www.worldometers.info/coronavirus/country/" + country + "/";
        Document doc=Jsoup.connect(url).get();
        //#main-counter wrap
        Elements elements = doc.select("#maincounter-wrap");
        elements.forEach((e)->{
           String text= e.select("h1").text();
           String count=e.select(".maincounter-number>span").text();
            br.append(text).append(count).append("<br>");
        });
        br.append("</html");
        return br.toString();
    }
    public static void main( String[] args ) throws Exception
    {
//        Scanner sc= new Scanner(System.in);
//        System.out.println( "Enter The Country" );
//        String co=sc.nextLine();
//        getData(co);

        // Creating GUI
        JFrame root=new JFrame("Details of COVID CASES");
        root.setSize(500,500);

        Font f=new Font("Poppins",Font.BOLD,30);

        //text field
        JTextField field=new JTextField();

        //label
        JLabel dataL=new JLabel();
        field.setFont(f);
        dataL.setFont(f);

        //Align the search bar text in center
        field.setHorizontalAlignment(SwingConstants.CENTER);
        dataL.setHorizontalAlignment(SwingConstants.CENTER);

        //Button
        JButton button=new JButton("Get");
        //adding listener to button
        button.addActionListener(event->{
            try{
                String maindata=field.getText();
                String result= getData(maindata);
                dataL.setText(result);
            }catch(Exception e)
            {
                e.printStackTrace();
            }

        });
        button.setFont(f);

        //Allocating position in main layout
        root.setLayout(new BorderLayout());
        root.add(field,BorderLayout.NORTH);
        root.add(dataL,BorderLayout.CENTER);
        root.add(button,BorderLayout.SOUTH);

        //show main window
        root.setVisible(true );


    }
}
