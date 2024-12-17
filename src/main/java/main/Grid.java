package main;
import eduni.simjava.*;

public class Grid {
    public static void main(String[] args) {
        Sim_system.initialise();
        Client client = new Client("Client", 1.0); // Установим одну задачу для клиента
        CE ce = new CE("CE", 2.0);

        // Создаем три узла с разной производительностью (разные задержки)
        WN wn1 = new WN("WN_1", 10.0); // Узел с меньшей задержкой (более производительный)
        WN wn2 = new WN("WN_2", 20.0); // Узел средней производительности
        WN wn3 = new WN("WN_3", 30.0); // Узел с большей задержкой (менее производительный)

        // Устанавливаем ссылки между Client, CE и каждым узлом
        Sim_system.link_ports("Client", "Out", "CE", "In1");
        Sim_system.link_ports("CE", "Out1", "WN_1", "In");
        Sim_system.link_ports("CE", "Out2", "WN_2", "In");
        Sim_system.link_ports("CE", "Out3", "WN_3", "In");

        // Обратные связи от узлов к CE и от CE к клиенту
        Sim_system.link_ports("WN_1", "Out", "CE", "In5");
        Sim_system.link_ports("WN_2", "Out", "CE", "In6");
        Sim_system.link_ports("WN_3", "Out", "CE", "In7");
        Sim_system.link_ports("CE", "OutResult", "Client", "In");

        Sim_system.set_trace_detail(false, true, false);
        Sim_system.run();
    }
}
