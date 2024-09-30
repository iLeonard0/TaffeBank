package unipar.edu.taffebank

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TransacaoAdapter(private val transacoes: List<Transacao>) : RecyclerView.Adapter<TransacaoAdapter.TransacaoViewHolder>() {

    class TransacaoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDescricao: TextView = itemView.findViewById(R.id.tvDescricao)
        val tvValor: TextView = itemView.findViewById(R.id.tvValor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransacaoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transacao, parent, false)
        return TransacaoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransacaoViewHolder, position: Int) {
        val transacao = transacoes[position]
        holder.tvDescricao.text = transacao.descricao
        holder.tvValor.text = String.format("R$ %.2f", transacao.valor)

        holder.tvValor.setTextColor(if (transacao.tipo == "Débito") android.graphics.Color.RED else android.graphics.Color.GREEN)
    }

    override fun getItemCount() = transacoes.size
}
