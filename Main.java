import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Pagamento> pagamentos = new ArrayList<>();
        pagamentos.add(new Pix("PIX-001", 150.00, LocalDate.of(2026, 8, 10)));
        pagamentos.add(new CartaoCredito("CC-002", 500.00, LocalDate.of(2026, 9, 1), 4));
        pagamentos.add(new Boleto("BOL-003", 300.00, LocalDate.of(2026, 6, 15)));
        pagamentos.add(new Pix("PIX-004", 80.00, LocalDate.of(2026, 9, 20)));
        pagamentos.add(new CartaoCredito("CC-005", 1200.00, LocalDate.of(2026, 9, 5), 1));

        System.out.println("===== NOTIFICAÇÕES =====");
        for (Pagamento p : pagamentos) {
            p.enviarNotificacao();
        }

        System.out.println("\n===== ORDENADO POR VALOR TOTAL (ordem natural) =====");
        List<Pagamento> porValor = new ArrayList<>(pagamentos);
        Collections.sort(porValor);
        for (Pagamento p : porValor) {
            System.out.println(p);
        }

        System.out.println("\n===== ORDENADO POR DATA =====");
        Comparator<Pagamento> porData = new Comparator<Pagamento>() {
            @Override
            public int compare(Pagamento p1, Pagamento p2) {
                return p1.getData().compareTo(p2.getData());
            }
        };

        List<Pagamento> porDataLista = new ArrayList<>(pagamentos);
        porDataLista.sort(porData);
        for (Pagamento p : porDataLista) {
            System.out.println(p);
        }

        System.out.println("\n===== TENTATIVAS DE ESTORNO =====");
        for (Pagamento p : pagamentos) {
            if (p instanceof Estornavel) {
                Estornavel estornavel = (Estornavel) p;
                boolean sucesso = estornavel.estornar();
                System.out.println(p.getIdTransacao() + " (" + p.getClass().getSimpleName()
                        + ") -> estorno " + (sucesso ? "APROVADO" : "NEGADO"));
            } else {
                System.out.println(p.getIdTransacao() + " (" + p.getClass().getSimpleName()
                        + ") -> não é estornável");
            }
        }

        System.out.println("\n===== CÓDIGOS DE RASTREIO (desafio extra) =====");
        for (Pagamento p : pagamentos) {
            imprimirRastreioSeDisponivel(p);
        }
    }

    private static void imprimirRastreioSeDisponivel(Object obj) {
        if (obj instanceof Rastreavel) {
            Rastreavel r = (Rastreavel) obj;
            System.out.println(obj.getClass().getSimpleName() + " -> " + r.codigoRastreio());
        } else {
            System.out.println(obj.getClass().getSimpleName() + " -> não possui rastreio");
        }
    }
}
