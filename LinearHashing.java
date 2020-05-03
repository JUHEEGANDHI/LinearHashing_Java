package advdatastructureshashing;
import java.io.*;
import java.util.*;
public class LinearHashing{
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br=new BufferedReader(new FileReader("E:\\Datafile\\1\\Users\\JUHEE LALIT GANDHI\\Desktop\\ADS\\hashfile1.txt"));
        ArrayList<String> tok=new ArrayList<>();
        String str;
        String[] arr;
        int count=0,choice;
        ArrayList<Integer> key=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        hashing h;
        while((str=br.readLine())!=null){
            arr=str.split("[^a-zA-Z]");
            for (String arr1 : arr) {
                if (arr1.equals("") || arr1.equals("\n")) {
                    continue;
                }
                if (!tok.contains(arr1)){
                    tok.add(arr1);
                    count++;
                }
            }   
        }
        do{
        System.out.println("Choose from the following operations ");
        System.out.println("1.Insert"+'\t'+"2.Delete"+'\t'+"3.Display"+'\t'+"0.Exit");
        choice=sc.nextInt();
        switch(choice){
            case 1:System.out.println("Enter the value to be added ");
                   String newval=sc.next();
                   tok.add(newval);
                   h=new hashing(tok);
                   h.hash();
                   System.out.println("Value inserted");
                   break;
            case 2:System.out.println("Enter the value to be deleted ");
                   String delval=sc.next();
                   boolean stat;
                   h=new hashing(tok);
                   stat=h.delete(delval);
                   h.hash();
                   if(stat)
                        System.out.println("Value deleted");
                   else
                        System.out.println("No such value in table");
                   break;
            case 3: h=new hashing(tok);
                    h.hash();
                    h.display();
                    break;
            case 0:break;
        }
        }while(choice!=0);
    }
}
    class hashing{
        int i,capacity=40;
        String arrval[]=new String[capacity]; 
        int collision[]=new int[capacity];
        ArrayList<String> tok=new ArrayList();
        ArrayList<Integer> key=new ArrayList();
        public hashing(ArrayList<String> tok){
            this.tok=tok;
        }
        void hash(){
        for(int k=0;k<tok.size();k++){
           String token=tok.get(k);
           int value=(int)token.charAt(0);
           key.add(value%capacity);   
        }
           tableform();
        }
         void tableform(){
         int count=0,count1=0;
         for(i=0;i<key.size();i++){
             int temp=key.get(i);
             count=0;
             if(arrval[temp]==null){
                arrval[temp]=tok.get(i);
                collision[temp]=1;
             }
             else
             {
                do{
                     count++;
                     temp=(temp+1)%capacity;
                 }while(arrval[temp]!=null);
                arrval[temp]=tok.get(i);
                collision[temp]=count+1;
             }
             
         }
        } 
        void display(){
            System.out.println("------------Hashing table--------------");
            System.out.format("%5s%15s%25s","Location","Value","Store time");
            System.out.println();
            for(i=0;i<arrval.length;i++){
                System.out.format("%5d%15s%25d",i,arrval[i],collision[i]);
                System.out.println();
            }
        }
        boolean delete(String str){
            int index=0;
            boolean status=false;
            for(String strtok:tok){
                if(strtok.equals(str)){
                    index=tok.indexOf(strtok);
                    status=true;
                }
            }
            if(status){
                tok.remove(index);
                return true;
            }
            else
                return false;
        }
    }
    

