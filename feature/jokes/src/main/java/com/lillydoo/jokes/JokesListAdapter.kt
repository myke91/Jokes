package com.lillydoo.jokes

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.lillydoo.jokes.data.model.JokeType
import com.lillydoo.jokes.data.model.Jokes
import com.lillydoo.shared.navigation.NavigationType
import com.lillydoo.shared.navigation.Navigator

/*
* Initializing adapter with list of data.
* Should use PagingDataAdapter(https://developer.android.com/reference/kotlin/androidx/paging/PagingDataAdapter)
* if dynamic list of data is to be provided
**/
class JokesListAdapter(private val jokesList: List<Jokes>) :
    RecyclerView.Adapter<JokesListAdapter.JokesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.joke_item, parent, false)
        return JokesViewHolder(view)
    }

    override fun getItemCount() = jokesList.size

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        val joke = jokesList[position]

        enableViews(holder, joke.type)
        if (joke.type == JokeType.SINGLE) {
            holder.joke.text = joke.joke
        } else {
            holder.jokeSetup.text = joke.setup
            holder.jokeDelivery.text = joke.delivery
        }

        //id, type, setup, punchline).
        val details = String.format("ID: %s\nTYPE: %s\nSETUP: %s\nPUNCHLINE: %s\n", joke.id, joke.type, joke.setup, joke.delivery)

        holder.itemView.setOnClickListener {
            Navigator(it.context).navigate(NavigationType.JOKES, details)
        }
    }

    private fun enableViews(holder: JokesViewHolder, type: JokeType) {
        if (type == JokeType.SINGLE) {
            holder.joke.isVisible = true
            holder.jokeSetup.isVisible = false
            holder.jokeDelivery.isVisible = false
        } else {
            holder.joke.isVisible = false
            holder.jokeSetup.isVisible = true
            holder.jokeDelivery.isVisible = true
        }
    }

    class JokesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var joke: TextView = itemView.findViewById(R.id.joke)
        var jokeSetup: TextView = itemView.findViewById(R.id.joke_setup)
        var jokeDelivery: TextView = itemView.findViewById(R.id.joke_delivery)
    }
}