/**
 *Mohmmed Mohammed
 */
import java.awt.Font; //needed it to set the Font for the Text Drawing
public class PokerTesterWindow{//PokerTesterWindow class
    //sortArr takes array of int representing the Poker Hand, then it order them regarding to its postion as element%13 
    public static void sortArr(int[] hand){//sortArr
        int min=hand[0]%13;
        int index=0;
        int temp;
        for(int i=0;i<hand.length;i++){
            index=i;
            min=hand[i]%13;
            for(int x=i;x<hand.length;x++){ 
                if(hand[x]%13<min){
                    min=hand[x]%13;
                    index=x;
                }
            }//end of for 
            temp=hand[i];
            hand[i]=hand[index];
            hand[index]=temp;
                
        }//end of for
        
    }//end of sortArr method
    
    
    //drawHand takes array of int representing the Poker Hand, to determine its rank and its suit and then Draw them out
    public static void drawHand(int [] hand){//drawHand method
    
        StdDraw.setPenColor(200,100,0);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 20));
        sortArr(hand);
        String han="Hand: ["+hand[0];
        for(int i=1;i<hand.length;i++)
            han=han+", "+hand[i];
            
        han=han+" ]";
        StdDraw.text(15, 34, han);
        
        String pic="";    
        String rank;
        String ArrayOfPic[]=new String[5];
        int handd[]=new int[5];
        for(int i=0,z=4;i<hand.length;i++,z+=6){            
            switch(hand[i]/13){
                case 0:{pic=((hand[i]%13)+1)+"h"; break;}
                case 1:{pic=((hand[i]%13)+1)+"d";break;}
                case 2:{pic=((hand[i]%13)+1)+"s";break;}
                case 3:{pic=((hand[i]%13)+1)+"c";break;}
                default:pic="";
            }//end of switch
            ArrayOfPic[i]=pic;
        }//end of for loop
        StdDraw.picture(4, 28, "\\CardDecks\\Paris\\"+ArrayOfPic[0]+".png", 7,10,0);
        StdDraw.picture(10.5, 28, "\\CardDecks\\Paris\\"+ArrayOfPic[1]+".png", 7,10,0);
        StdDraw.picture(17, 28,  "\\CardDecks\\Paris\\"+ArrayOfPic[2]+".png", 7,10,0);
        StdDraw.picture(23.5,28, "\\CardDecks\\Paris\\"+ArrayOfPic[3]+".png", 7,10,0);
        StdDraw.picture(30, 28, "\\CardDecks\\Paris\\"+ArrayOfPic[4]+".png", 7,10,0);
    }//end of drawHand method
    
    
    //DrawHandText takes array of int "Hand" and then print them out in text format 
    
    public static void drawHandText(int [] hand){//drawHandText method
        StdDraw.setPenColor(50,50,0);
        StdDraw.setFont(new Font("Arial", Font.BOLD, 13));
        String suit,rank;
        String prin="";
        for(int i=0;i<hand.length;i++)
            prin+=determineRankOfCard(hand[i])+" of "+determineSuit(hand[i])+"      ";
        
        StdDraw.text(17,22,prin);
              
    }//end of drawHandText method
    
    
    //isSameKind takes array of int "hand", then identify the cards
     //if they on the same suit or not and retrun true or false regarding to that  
    
    public static boolean isSameKind(int []hand){
        int cont;
        for(int i=0;i<4;i++){
            cont=0;
            for(int j=0;j<hand.length;j++){
                if(hand[j]/13==i)
                    cont++;
            }//end of for loop
            if(cont==5)
                return true;
        } //end of for loop
            
        return false;
    }//end of isSameKind method
    
    //drawIdentifiedHand takes array of int "hand", then
    //call identifyHand method with the hand, then Draw out the return determined kind
    
    public static void drawIdentifiedHand(int [] hand){//drawIdentifiedHand
        String Identifyhand=identifyHand(hand);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 25));
        StdDraw.text(17, 15, "You have ");
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 30));
        StdDraw.text(17,10,Identifyhand);
    }//end of drawIdentifiedHand
    
    //identifyHand takes int array representing the Hand, 
    //then determine the kind of hand and return it, as String 
    
    public static String identifyHand(int[] hand){
        
        boolean isItThreeOfKind= false;
        boolean isItStraight= true;
        boolean isItStraightFlush= true;
        int isItFullHouse=0;
        int [] fourOrFullHouse=new int[5];
        int count=10;
        
        for(int i=0,j=0;i<hand.length-1;i++){
            if(!(hand[i]==(hand[i+1]-1)))
                isItStraightFlush=false;
                
             if(!(hand[i]%13==((hand[i+1]%13)-1)))
                 isItStraight=false;
                 
             if(hand[i]%13==hand[i+1]%13)
             {
                 isItFullHouse++;
                 fourOrFullHouse[j++]=hand[i];
                 
             }
             else
                 count=i;
        }//end of for loop
        
        if(isItStraightFlush||(isSameKind(hand)&&isItStraigthAceTop(hand))){
            return "Straight flush";
        }//end of if
        else
        if(isItFullHouse==3){
            if(count==0||count==3)
                return "Four kind of "+determineRankOfCard(fourOrFullHouse[0])+"s";
            else 
                return ((fourOrFullHouse[0]%13)==(fourOrFullHouse[1])%13)? ""+determineRankOfCard(fourOrFullHouse[0])+"s Full of "+determineRankOfCard(fourOrFullHouse[2])+"s" :determineRankOfCard(fourOrFullHouse[2])+"s Full of "+determineRankOfCard(fourOrFullHouse[0])+"s" ;
        }//end of if 
        else
        if(isSameKind(hand))
            return "Flush";
        else
        if(isItStraight||isItStraigthAceTop(hand))
            return "Straight";
        else{
            boolean co=false;
            int forTwo=0;
            int ofSuit[]=new int[2];
            for(int i=0,j=0;i<hand.length-1;i++){
                if(hand[i]%13==hand[i+1]%13){ 
                    if(co)
                        isItThreeOfKind=true;
                        
                    ofSuit[j++]=hand[i];    
                    co=true;
                    forTwo++;
                }//end of if
                else
                    co=false;
            }//end of for loop
            if(isItThreeOfKind)
                return "Three kind of "+determineRankOfCard(ofSuit[0])+"s";
            else
            if(forTwo==2)
                return "Two Pairs of "+determineRankOfCard(ofSuit[0])+"s and "+determineRankOfCard(ofSuit[1])+"s";
            else
            if(forTwo==1)
                return "Pair of "+determineRankOfCard(ofSuit[0])+"s";
            
            else
                return "High Card";
               
        }//end of else
        
    }//end of identifyHand method
    
    
       
    //isItStraigthAceTop takes an array of int "hand", checks the Straight case when the Ace on Top, 
    //and return boolean type regard to that 
    
    public static boolean isItStraigthAceTop(int []hand){
    
        int han[]=new int[5]; 
        
        //for loop copy hand into new array, whenever find Ace card, change its postion to 
        //the king postion, else will push all the card one number down
        
        for(int i=0;i<hand.length;i++){
            if(hand[i]%13==0)
                han[i]=hand[i]+12;
            else
                han[i]=hand[i]-1;
        }//end of for loop
        
        sortArr(han);
        
        //then try to determine is the new arrary is striagth or not
        for(int i=0;i<han.length-1;i++)
            if(han[i]%13!=han[i+1]%13-1)
                return false;
                
        return true;
    }//end of isItStraigthAceTop method
    
    //determineRankOfCard takes one card as int, determine its Rank, then return it as String type
    
    public static String determineRankOfCard(int card){
        String rank="";
        switch(card%13){
            case 0:{rank="Ace"; break;}
            case 1:{rank="2"; break;}
            case 2:{rank="3"; break;}
            case 3:{rank="4"; break;}
            case 4:{rank="5"; break;}
            case 5:{rank="6"; break;}
            case 6:{rank="7"; break;}
            case 7:{rank="8"; break;}
            case 8:{rank="9"; break;}
            case 9:{rank="10"; break;}
            case 10:{rank="Jack"; break;}
            case 11:{rank="Queen"; break;}
            case 12:{rank="King"; break;}
            default: rank="wrong choice";
       }//end of switch
        return rank;
    }//end of determineRankOfCard method
//determineSuit takes one card as int, determine its Suit, then return it as String type      
    public static String determineSuit(int card){
        String suit="";
        switch(card/13){//switch
            case 0:{suit="hearts"; break;}
            case 1:{suit="diamonds";break;}
            case 2:{suit="spades";break;}
            case 3:{suit="clubs";break;}
            default:suit="wrong choice";
            }//end of switch
            return suit;
    }
     //main start up point of the program takes array of String as command line input and
     //implement poker Game by calling (drawHand,drawHandText,drawIdentifiedHand) with preconfigured hand, and no return
    
    public static void main(String []args){// main method
        StdDraw.setXscale(0,34);
        StdDraw.setYscale(0,35);
        //int[] testHand={0, 1, 2, 3,4};
        //int[] testHand={2, 8, 0, 27,16 };//stright flush
        //int[] testHand={0, 40, 48, 23, 13};//pair
        //int[] testHand={0, 40, 48, 26, 13};//three of kind 7
        int[] testHand={35, 1, 48, 22, 13};
        //int[] testHand={0, 35, 23, 37, 12};
       // int[] testHand={0, 4, 14, 29, 15};
        //int[] testHand={4, 13, 26, 39, 30};
        //int[] testHand={27, 35, 48, 22, 13};
       // int[] testHand={0, 2, 14, 17, 10};
       // int[] testHand={35, 32, 34, 36, 33};
        drawHand(testHand);//This will draw corresponding images in the window
        drawHandText(testHand);//This will draw names of each card under the images
        drawIdentifiedHand(testHand);//This will print what hand you have in the window              
    }//end of main
    
}//end of PokerTesterWindow class