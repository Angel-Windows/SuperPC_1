package main;
import eduni.simjava.*;

public class CE extends Sim_entity {
    private Sim_port in1, in2, in5, in6, in7, in8;
    private Sim_port out1, out2, out3, out4, outResult;
    private double delay;
    public static Boolean link = false;
    private int taskCounter = 0;

    CE(String name, double delay) {
        super(name);
        this.delay = delay;
        in1 = new Sim_port("In1");
        in2 = new Sim_port("In2");
        in5 = new Sim_port("In5");
        in6 = new Sim_port("In6");
        in7 = new Sim_port("In7");
        in8 = new Sim_port("In8");

        out1 = new Sim_port("Out1");
        out2 = new Sim_port("Out2");
        out3 = new Sim_port("Out3");
        out4 = new Sim_port("Out4");
        outResult = new Sim_port("OutResult");

        add_port(in1);
        add_port(in2);
        add_port(in5); add_port(in6); add_port(in7); add_port(in8);
        add_port(out1); add_port(out2); add_port(out3); add_port(out4);
        add_port(outResult);
    }

    @Override
    public void body() {
        while (Sim_system.running()) {
            Sim_event event = new Sim_event();
            sim_wait(event);

            if (event.from_port(in1)) {
                sim_process(delay);
                sim_completed(event);

                // Равномерное распределение задач по узлам
                switch (taskCounter % 4) {
                    case 0:
                        sim_schedule(out1, delay, taskCounter);
                        break;
                    case 1:
                        sim_schedule(out2, delay, taskCounter);
                        break;
                    case 2:
                        sim_schedule(out3, delay, taskCounter);
                        break;
                    case 3:
                        sim_schedule(out4, delay, taskCounter);
                        break;
                }

                sim_trace(Sim_system.get_trc_level(), "Workload No." + taskCounter + " from CE has been sent...");
                taskCounter++;
            }

            // Получение результатов от всех узлов
            if (event.from_port(in5) || event.from_port(in6) || event.from_port(in7) || event.from_port(in8)) {
                link = true;
                sim_trace(Sim_system.get_trc_level(), "The output directory generated!");
                sim_schedule(outResult, 3.0, 0); // Отправка результата обратно клиенту
            }
        }
    }
}
