package com.example.kode_introductory_task.presentation

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.kode_introductory_task.R
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

import java.util.*

@BindingAdapter("firstName", "lastName")
fun bindFullName(textView: TextView, firstName: String, lastName: String){
    textView.text = String.format(
        textView.context.getString(R.string.full_name_sample),
        firstName,
        lastName
    )
}

@BindingAdapter("birthDate")
fun bindBirthDate(textView: TextView, birthDate: String){
    val formatter = SimpleDateFormat("yyyy-MM-dd").parse(birthDate)
    textView.text = DateFormat.getDateInstance(SimpleDateFormat.LONG,  Locale("ru"))
        .format(formatter)
}



@BindingAdapter("age")
fun bindAge(textView: TextView, birthDate: String){
    val periodFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val period = Period.between(LocalDate.parse(birthDate, periodFormatter), LocalDate.now())
    val yearsSample = when(period.years % 10){
        1 -> textView.context.getString(R.string.year_sample)
        2,3,4 -> textView.context.getString(R.string.years_sample)
        else -> textView.context.getString(R.string.lots_of_years_sample)
    }
    textView.text = String.format(
        yearsSample,
        period.years.toString()
    )
}

@BindingAdapter("phoneNumber")
fun bindPhoneNumber(textView: TextView, number: String) {
    textView.text = String.format(
        textView.context.getString(R.string.phone_sample),
        number
    )
}