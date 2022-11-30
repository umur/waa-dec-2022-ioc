package application;
import myFramework.MyAutoWired;
import myFramework.MyBean;




@MyBean
public class Class2 {
        @MyAutoWired
        private Class1 c1;
        public void print(){
            c1.print();
            System.out.println("Hello Class2");
        }
    }

