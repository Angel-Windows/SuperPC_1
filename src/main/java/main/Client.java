package main;
import eduni.simjava.*;

public class Client extends Sim_entity {
    private Sim_port in, out;
    private double delay;
    public static int jobs_count = 2; // Установлено количество задач

    Client(String name, double delay) {
        super(name);
        this.delay = delay;
        in = new Sim_port("In");
        out = new Sim_port("Out");
        add_port(in);
        add_port(out);
    }

    @Override
    public void body() {
        // Отправляем первую задачу
        sim_schedule(out, 0.0, 0);
        sim_trace(Sim_system.get_trc_level(), "jdl-file has been sent...");
        sim_pause(10.0); // Пауза для согласования времени отправки

        for (int i = 1; i < jobs_count; i++) {
            sim_schedule(out, 3.0 + (i * 3.0), i); // Каждая следующая задача
            sim_trace(Sim_system.get_trc_level(), "jdl-file has been sent...");
        }

        // Ожидание результата
        Sim_event event = new Sim_event();
        sim_wait(event);
        if (event.from_port(in) && CE.link) {
            CE.link = false;
            sim_trace(Sim_system.get_trc_level(), ">> Result link received!");
            System.out.println("Done!");
            sim_completed(event);
        }
    }
}
