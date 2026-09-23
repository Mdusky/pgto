import java.time.LocalDate;

public abstract class Pagamento implements Comparable<Pagamento>, Notificavel {

    protected String idTransacao;
    protected double valor;
    protected LocalDate data;

    public Pagamento(String idTransacao, double valor, LocalDate data) {
        this.idTransacao = idTransacao;
        this.valor = valor;
        this.data = data;
    }

    public abstract double calcularTaxa();

    public double valorTotal() {
        return valor + calcularTaxa();
    }

    @Override
    public int compareTo(Pagamento outro) {
        return Double.compare(this.valorTotal(), outro.valorTotal());
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | valor=R$%.2f | taxa=R$%.2f | total=R$%.2f | data=%s",
                idTransacao, getClass().getSimpleName(), valor, calcularTaxa(), valorTotal(), data);
    }
}
