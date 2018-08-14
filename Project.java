import java.util.*;
import java.io.*;
import java.sql.*;
public class Project
{
     public static void main(String args[])throws IOException
     {
         Scanner sc=new Scanner(System.in);
         Connection con=null;
         Statement stmt=null;
        boolean a=true;
       // PreparedStatement st;
       File file = new File("asd.txt");
       int j=0;
        try
        {
           Class.forName("com.mysql.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cal","root","root");  
           
            stmt=con.createStatement(); 
            // st =(PreparedStatement) con.prepareStatement("insert into data(str,event)"+" values(?,?)");
        }
        catch(Exception e){
            System.out.println(e);
        }
                
           
         
      
       while(a)
       {  
           System.out.println("enter 1-for dispaly of particular month\n 2 -for dispaly for printing of whole year calendar \n 3 -for setting reminder \n 4-for displaying of all the reminder set for a particular event \n5- for printing all reminders\n 6 for exit");
          System.out.print("------enter-----");
           int n=sc.nextInt();
           sc.nextLine();
           switch(n)
         {
          case 1:   
                        displayMonth();
                        break;
          case 2:
                    displayYear();
                    break;
          case 3:    System.out.println("enter the date for setting the reminder in dd/mm/yy format");
                    String content=sc.nextLine();
                    //sc.nextLine();
                    System.out.println("enter the event on that day");
                    String eve=sc.nextLine();
                    System.out.println("enter the name of the person");
                    String name=sc.nextLine();
                   // sc.nextLine();
                   try{
                       FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
                       BufferedWriter bw = new BufferedWriter(fw);
                       bw.append(content);
                       bw.append("\r\t");
                       bw.append(eve);
                       bw.append("\r\t");
                       bw.append(name);
                       bw.append("\r\n");
                       bw.close();
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                   try{
                       j++;
                    stmt.executeUpdate("insert into data values('"+content+ "','"+eve +"','"+name+"')");
                   }
                   catch(Exception e)
                   {
                       System.out.println(e.getMessage());
                   }
                     break;
          case 4:
                   if(j==0)
                        System.out.println("database empty"); 
                 else
                   {
                   // Scanner s = new Scanner(file); 
                    //while (s.hasNextLine())
                    //System.out.println(s.nextLine());  
                    System.out.println("enter the event you want to search"); 
                    String ev=sc.nextLine();
                    //sc.nextLine();
                    
                    try
                    {
                        int i=0;
                        ResultSet rs=stmt.executeQuery("select * from data");  
                         while(rs.next())  
                            {
                                if(rs.getString(2).equalsIgnoreCase(ev))
                                    {
                                        i++;
                                        System.out.print("date of the event-------"+rs.getString(1)+"\t");
                                        System.out.println("name of the person-------"+rs.getString(3));
                                    }
                             }
                         if(i==0)
                             System.out.println("Data not found");
                    } 
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                   }
                   break;   
                    
          case 5:      
              try{
                  ResultSet p=stmt.executeQuery("select * from data");  
                        if(p.first())
                        {
                            j++;
                        }
              }
                        catch(Exception e){};
                        
                 //while(p.next())  
                            //{
                             //  if(p.getString(2)!=null)
                                 //   {
                                    //    j++;
                                    //}
                                        
                             //}
                      if(j==0)
                        System.out.println("database empty"); 
                 else
                   {
                   
                    
                    try
                    {
                       
                        ResultSet rs=stmt.executeQuery("select * from data");  
                         while(rs.next())  
                            {
                               
                                        System.out.print("date of the event-------"+rs.getString(1)+"\t");
                                        System.out.print("date of the event-------"+rs.getString(2)+"\t");
                                        System.out.println("name of the person-------"+rs.getString(3));
                                   
                             }
                         
                    } 
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                   }
                    break;               
          default:  a=false;
                    System.out.println("You have entered  choice to exit\n-------Exiting-------");
        }
        
     }
    }
     static void  displayMonth()
     {
          Scanner obj=new Scanner(System.in);
          int count=2,point=0,k=1;
          System.out.println("Enter a year greater than 1900");
          int yr=obj.nextInt();
          System.out.println("Enter a month");
          int mn=obj.nextInt();
          int x=1,yrr=1900,mnn=1;
          if(yr<1900 || mn>12)
          {
                System.out.print("Invalid input\n");
          }
          else
          {
                while(yrr>=1900 && yrr<=yr)
                {
                      mnn=1;
                      while((mnn>=1 && mnn<=12) )
                      {
                            if(yrr==yr && mnn==mn)
                            break;
                            x=0;
                            if(mnn==1 || mnn==3 || mnn==5 || mnn==7 || mnn==8 || mnn==10 ||mnn==12)
                            {
                                 point=31;
                            }
                            else if(mnn==4 || mnn==6 || mnn==9 || mnn==11)
                            {
                                 point=30;
                            }
                            else if(mnn==2 && yrr%4!=0 )
                            {
                                 point=28; 
                            }
                            else if(mnn==2 && yrr%4==0)
                            {
                                 point=29;
                                 if(yrr%100==0 && yrr%400!=0)
                                 point=28;
                                 else if(yrr%100==0 && yrr%400==0)
                                 point=29;
                            }
                            if(count==6 && point==31)
                            {
                                 count=2;
                                 mnn++;
                                 continue;
                            }
                            if(count==7 && point==30)
                            {
                                 count=2;
                                 mnn++;
                                 continue;
                            }
                            if(count==7 && point==31)
                            {
                                 count=3;
                                 mnn++; 
                                 continue;
                            }
                            if(count==8 && point==29)
                            count=1;
                            if(count==8 && point==30)
                            count=1;
                            if(count==8 && point==31)
                            count=1;
                            k=count-1;
                            count=1;
                            for(int i=1;i<=5;i++)
                            {
                                 if(i<=4)
                                 {
                                        for(int l=1;l<=7-k;l++)
                                        {
                                             x++;
                                        }
                                 }
                                 else 
                                 {
                                        for(int l=1;x<point;l++)
                                        {
                                             x++;
                                             count++;
                                        }
                                 }
                                 k=0;      
                            }
                            mnn++;
                     }
                     yrr++;
               }
          
         int z=count-1,n=1;
         if(mnn==1 || mnn==3 || mnn==5 || mnn==7 || mnn==8 || mnn==10 ||mnn==12)
         {
               point=31;
         }
         else if(mnn==4 || mnn==6 || mnn==9 || mnn==11)
         {
               point=30;
         }
         else if(mnn==2 && (yrr-1)%4!=0 )
         {
               point=28; 
         }
         else if(mnn==2 && (yrr-1)%4==0)
         {
               point=29;
               if((yrr-1)%100==0 && (yrr-1)%400!=0)
               point=28;
               else if((yrr-1)%100==0 && (yrr-1)%400==0)
               point=29;
         }
         System.out.print("Sun\tMon\tTue\tWed\tThu\tFri\tSat\n");
         for(int j=1;j<=z;j++)
         {
               System.out.print("\t");
         }
         for(int i=1;i<=6;i++)
         {
               for(int l=1;l<=7-z;l++)
               {
                     System.out.print(n+"\t");
                     n++;
                     if(n >point)
                     break;
               }
               z=0; 
               System.out.println();
               if(n>point)
               {
                        System.out.println();
                        System.out.println();
                        break;
               }
          }
       } 
     } 
     static void displayYear()
     {
          Scanner obj=new Scanner(System.in);
          System.out.println("Enter a year greater than 1900");
          int yr=obj.nextInt(),mn=0;
          for(int q=1;q<=12;q++)
          {  
                int count=2,point=0,k=1;
                int x=1,yrr=1900,mnn=1;
                if(yr<1900)
                {
                       System.out.print("Invalid input\n");
                       break;
                }
                else
                { 
                       mn++;
                       switch(mn)
                       {
                              case 1:System.out.println("\t\t\tJanuary,"+yr);
                              break;
                              case 2:System.out.println("\t\t\tFebruary,"+yr);
                              break;
                              case 3:System.out.println("\t\t\tMarch,"+yr);
                              break;
                              case 4:System.out.println("\t\t\tApril,"+yr);
                              break;
                              case 5:System.out.println("\t\t\tMay,"+yr); 
                              break;
                              case 6:System.out.println("\t\t\tJune,"+yr);
                              break;
                              case 7:System.out.println("\t\t\tJuly,"+yr);
                              break;
                              case 8:System.out.println("\t\t\tAugust,"+yr);
                              break;
                              case 9:System.out.println("\t\t\tSeptember,"+yr);
                              break;
                              case 10:System.out.println("\t\t\tOctober,"+yr);
                              break;
                              case 11:System.out.println("\t\t\tNovember,"+yr);
                              break;
                              case 12:System.out.println("\t\t\tDecember,"+yr);
                              break;
                        }
                        while(yrr>=1900 && yrr<=yr)
                        {
                              mnn=1;
                              while((mnn>=1 && mnn<=12) )
                              {
                                    if(yrr==yr &&  mnn==mn)
                                    break;
                                    x=0;
                                    if(mnn==1 || mnn==3 || mnn==5 || mnn==7 || mnn==8 || mnn==10 ||mnn==12)
                                    {
                                          point=31;
                                    }
                                    else if(mnn==4 || mnn==6 || mnn==9 || mnn==11)
                                    {
                                          point=30;
                                    }
                                    else if(mnn==2 && yrr%4!=0 )
                                    {
                                          point=28; 
                                    }
                                    else if(mnn==2 && yrr%4==0)
                                    {
                                          point=29;
                                          if(yrr%100==0 && yrr%400!=0)
                                          point=28;
                                          else if(yrr%100==0 && yrr%400==0)
                                          point=29;
                                    }
                                   if(count==6 && point==31)
                                   {
                                          count=2;
                                          mnn++;
                                          continue;
                                   }
                                   if(count==7 && point==30)
                                   {
                                          count=2;
                                          mnn++;
                                          continue;
                                   }
                                   if(count==7 && point==31)
                                   {
                                          count=3;
                                          mnn++; 
                                          continue;
                                   }
                                   if(count==8 && point==29)
                                   count=1;
                                   if(count==8 && point==30)
                                   count=1;
                                   if(count==8 && point==31)
                                   count=1;
                                   k=count-1;
                                   count=1;
                                   for(int i=1;i<=5;i++)
                                   {
                                         if(i<=4)
                                         {
                                                 for(int l=1;l<=7-k;l++)
                                                 {
                                                       x++;
                                                 }
                                          }
                                          else 
                                          {
                                                 for(int l=1;x<point;l++)
                                                 {
                                                        x++;
                                                        count++;
                                                 }
                                          }
                                          k=0;      
                                    }
                                    mnn++;
                              }
                              yrr++;
                        }
                        int z=count-1,n=1;
                        if(mn==1 || mn==3 || mn==5 || mn==7 || mn==8 || mn==10 ||mn==12)
                        {
                              point=31;
                        }
                        else if(mn==4 || mn==6 || mn==9 || mn==11)
                        {
                              point=30;
                        }
                        else if(mn==2 && (yrr-1)%4!=0 )
                        {
                              point=28; 
                        }
                        else if(mn==2 && (yrr-1)%4==0)
                        {
                              point=29;
                              if((yrr-1)%100==0 && (yrr-1)%400!=0)
                              {
                                    point=28;
                              }
                              else if((yrr-1)%100==0 && (yrr-1)%400==0)
                              {
                                    point=29;
                              }
                        }
                        System.out.print("Sun\tMon\tTue\tWed\tThu\tFri\tSat\n");
                        for(int j=1;j<=z;j++)
                        {
                              System.out.print("\t");
                        }
                        for(int i=1;i<=6;i++)
                        {
                              for(int l=1;l<=7-z;l++)
                              {
                                    System.out.print(n+"\t");
                                    n++;
                                    if(n >point)
                                    break;
                              }
                              z=0; 
                              System.out.println();
                              if(n>point)
                              {
                                    System.out.println();
                                    System.out.println();
                                    break;
                              }
                        }
                }
          }   
     } 
}
