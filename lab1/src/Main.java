public class Main {
    public static void main(String[] args) throws BeanNotFoundException, IllegalAccessException {

        MyInjector inject = new MyInjector();

        //ApplicationLevelClass ac  = (ApplicationLevelClass) inj.getMyBean("ApplicationLevelClass");
        ConsumerClass consumerClass  = (ConsumerClass) inject.getBean(ConsumerClass.class);

        //ApplicationLevelClass ac = new ApplicationLevelClass();
        //ConsumerClass cc = new ConsumerClass(ac);
        consumerClass.print();

    }
}