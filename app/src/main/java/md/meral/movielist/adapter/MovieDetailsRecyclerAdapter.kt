package md.meral.movielist.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import md.meral.movielist.R
import md.meral.movielist.model.ProductionCompanies
import md.meral.movielist.util.Constants.ORIGINAL_POSTER_SIZE

class MovieDetailsRecyclerAdapter(val context: Context): RecyclerView.Adapter<MovieDetailsRecyclerAdapter.ViewHolder>() {

    var productionCompanies: List<ProductionCompanies> = listOf()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val image: ImageView = view.findViewById(R.id.company_image)
        private val name: TextView = view.findViewById(R.id.company_name)

        @SuppressLint("SetTextI18n")
        fun setData(image: String?, name: String) {

            if (image != null) {
                Glide.with(itemView).load("$ORIGINAL_POSTER_SIZE$image").into(this.image)
            }

            this.name.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.production_companies_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val company = productionCompanies[position]

        holder.setData(company.logoPath, company.name)
    }

    override fun getItemCount(): Int {

        return productionCompanies.size
    }

    fun setCompanyInfo(body: List<ProductionCompanies>) {

        this.productionCompanies = body
        notifyDataSetChanged()
    }
}