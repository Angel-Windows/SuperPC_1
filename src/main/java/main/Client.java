package main;
import eduni.simjava.*;

public class Client extends Sim_entity {
    private Sim_port in, out;
    private double delay;
    public static int jobs_count = 3;

    Client(String name, double delay) {
        super(name);
        this.delay = delay;
        in = new Sim_port("In");
        out = new Sim_port("Out");
        add_port(in);
        add_port(out);
        System.out.println("Client created: " + name);
    }

    @Override
    public void body() {
        System.out.println("Client started...");
        for (int i = 0; i < jobs_count; i++) {
            sim_schedule(out, 3.0 + i * 10, i);
            System.out.println("Job " + i + " from client has been sent...");
            sim_pause(delay);
        }

        while (true) {
            Sim_event event = new Sim_event();
            sim_wait(event);
            if (event.from_port(in) && CE.link) {
                CE.link = false;
                System.out.println(">> Result link received from Client!");
                sim_completed(event);
                System.out.println("Client finished processing.");
                break; // Выходим из цикла после получения результата
            }
        }
    }
    }