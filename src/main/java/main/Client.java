package main;

import eduni.simjava.*;

public class Client extends Sim_entity {
    private Sim_port in, out;
    private double delay;
    private double currentTime; // Для отслеживания времени
    public static int jobs_count = 3;

    public Client(String name, double delay) {
        super(name);
        this.delay = delay;
        in = new Sim_port("In");
        out = new Sim_port("Out");
        add_port(in);
        add_port(out);
    }

    @Override
    public void body() {
        sendJdlFile();

        for (int i = 0; i < jobs_count; i++) {
            sim_schedule(out, 3.0, i);
            currentTime += 3.0; // Обновляем текущее время
            System.out.println("u: Client at " + formatTime(currentTime) + ": Job " + i + " has been sent...");
            sim_pause(delay);
        }
        Sim_event event = new Sim_event();
        sim_wait(event);
        if (event.from_port(in) && CE.link) {
            CE.link = false;
            System.out.println("u: Client at " + formatTime(currentTime) + ": >> Result link received!");
            System.out.println("Done!");
            sim_completed(event);
        }
    }

    private String formatTime(double time) {
        return String.format("%.1f", time);
    }

    public void sendJdlFile() {
        System.out.println("u: Client at " + formatTime(currentTime) + ": jdl-file has been sent...");

    }

    // Метод для получения результатов обработки
    public void receiveResults() {
        System.out.println("u: Client at " + formatTime(currentTime) + ": >> Result link received!");
        // Здесь можно добавить логику для обработки результатов
    }
}
