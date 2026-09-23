import java.time.LocalDate;

public class Boleto extends Pagamento implements Rastreavel {

    public Boleto(String idTransacao, double valor, LocalDate data) {
        super(idTransacao, valor, data);
    }

    @Override
    public double calcularTaxa() {
        return 3.49;
    }

    @Override
    public void enviarNotificacao() {
        System.out.println("[BOLETO] Boleto " + idTransacao + " gerado. Vencimento em "
                + data.plusDays(3) + ". Valor total: R$" + String.format("%.2f", valorTotal()));
    }
    
    @Override
    public String codigoRastreio() {
        return "23793.38128 60082.446018 91020.150008 1 " + (90000000000L + idTransacao.hashCode() % 9999999);
    }
}
