#include<stdio.h>
#include<stdlib.h>
#include  <sys/types.h>
#include  <sys/wait.h>
#include <unistd.h>
void main()
{
  int p1,p2,p3,p4,p5;
  p1=fork();
  if(p1==0)
    {//c1
	//printf("child c1 starts\n");	
	p2=fork();
	if(p2==0)
	  {//c3
		printf("\nchild c3 starts\n");
		c3();
		
	}
	else{//c1
		printf("\nparent c1 starts\n");	
		c1();
	 }
   }  
   else
    {//p


        p3=fork();
		if(p3==0)//c2
		  {

                	//printf("child c2 starts\n");
			p4=fork();
		      if(p4==0)//c4
		       {

                	  printf("\nchild c4 starts\n");
			   c4();
		        }
		     else{//c2

			 printf("\nparent c2 starts\n");
			 c2();
			}
		}
   if(p1!=0&&p2!=0&&p3!=0){
  	printf("\nparent p1 starts\n");	
        p();}
    }
}

void p()
{
  for(int i=1200;i<3000;i++)
	printf("\t%d           pid --%d\n",i,getpid());	
}
void c1()
{
  for(int i=200;i<600;i++)
	printf("\t%d           pid --%d\n",i,getpid());	
}
void c2()
{
  for(int i=600;i<1000;i++)
	printf("\t%d          pid --%d\n",i,getpid());	
}
void c3()
{
  for(int i=1;i<200;i++)
	printf("\t%d           pid --%d\n",i,getpid());	
}
void c4()
{
  for(int i=1000;i<1200;i++)
	printf("\t%d            pid --%d\n",i,getpid());	
}

