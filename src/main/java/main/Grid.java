package main;
import eduni.simjava.*;

public class Grid {
    public static void main(String[] args) {
        Sim_system.initialise();
        Client client = new Client("Client", 2.0);
        CE ce = new CE("CE", 2.0);

        // Создаем четыре узла с уникальными именами
        WN wn1 = new WN("WN_1", 50.0);
        WN wn2 = new WN("WN_2", 50.0);
        WN wn3 = new WN("WN_3", 50.0);
        WN wn4 = new WN("WN_4", 50.0);

        // Устанавливаем ссылки между Client, CE и каждым узлом
        Sim_system.link_ports("Client", "Out", "CE", "In1");
        Sim_system.link_ports("CE", "Out1", "WN_1", "In");
        Sim_system.link_ports("CE", "Out2", "WN_2", "In");
        Sim_system.link_ports("CE", "Out3", "WN_3", "In");
        Sim_system.link_ports("CE", "Out4", "WN_4", "In");

        // Обратные связи от узлов к CE и от CE к клиенту
        Sim_system.link_ports("WN_1", "Out", "CE", "In5");
        Sim_system.link_ports("WN_2", "Out", "CE", "In6");
        Sim_system.link_ports("WN_3", "Out", "CE", "In7");
        Sim_system.link_ports("WN_4", "Out", "CE", "In8");
        Sim_system.link_ports("CE", "OutResult", "Client", "In");

        Sim_system.set_trace_detail(false, true, false);
        Sim_system.run();
    }
}
