package br.com.lighttasks.presentation.compose.widgets.date_picker.model

enum class YearButtonState {
    IsSelected, IsCurrent, IsNotSelected;

    companion object {
        fun getYearState(year: Int, selectedYear: Int, startYear: Int): YearButtonState {
            return if (selectedYear == year)
                IsSelected
            else if (startYear == year)
                IsCurrent
            else
                IsNotSelected
        }
    }
}