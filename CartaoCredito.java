import java.time.LocalDate;

public class CartaoCredito extends Pagamento implements Estornavel {

    private int parcelas;

    public CartaoCredito(String idTransacao, double valor, LocalDate data, int parcelas) {
        super(idTransacao, valor, data);
        this.parcelas = parcelas;
    }

    @Override
    public double calcularTaxa() {
        double taxaPercentual = valor * 0.035;
        double taxaParcelamento = (parcelas > 1) ? (parcelas - 1) * 0.50 : 0.0;
        return taxaPercentual + taxaParcelamento;
    }

    @Override
    public void enviarNotificacao() {
        System.out.println("[CARTÃO] Pagamento " + idTransacao + " aprovado em "
                + parcelas + "x. Valor total: R$" + String.format("%.2f", valorTotal()));
    }

    @Override
    public boolean estornar() {
        return true;
    }

    public int getParcelas() {
        return parcelas;
    }
}
