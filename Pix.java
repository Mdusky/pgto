import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Pix extends Pagamento implements Estornavel {

    public Pix(String idTransacao, double valor, LocalDate data) {
        super(idTransacao, valor, data);
    }

    @Override
    public double calcularTaxa() {
        return 0.0;
    }

    @Override
    public void enviarNotificacao() {
        System.out.println("[PIX] Pagamento " + idTransacao
                + " confirmado instantaneamente. Valor: R$" + String.format("%.2f", valor));
    }

    @Override
    public boolean estornar() {
        long diasPassados = ChronoUnit.DAYS.between(data, LocalDate.now());
        return diasPassados <= 90;
    }
}
