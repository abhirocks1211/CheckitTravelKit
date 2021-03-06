package eu.szwiec.checkittravelkit.ui.search

import android.content.Context
import com.nhaarman.mockitokotlin2.mock
import eu.szwiec.checkittravelkit.prefs.Preferences
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.inject
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FavoritesViewModelTest : AutoCloseKoinTest() {

    private val header = "header"
    private val footer = "footer"

    private val context: Context by inject()
    private val preferences = mock<Preferences>()
    private val vm = FavoritesViewModel(context, preferences)

    @Test
    fun noItemsWhenNoFavorites() {
        val items = vm.getNewItems(emptySet(), header, footer)
        items.shouldBeEmpty()
    }

    @Test
    fun itemsAreCorrect() {
        val poland = "Poland"
        val germany = "Germany"

        val items = vm.getNewItems(setOf(poland, germany), header, footer)

        items.count() shouldEqual 4
        items[0] shouldEqual header
        (items[1] as FavoriteCountry).name shouldEqual germany
        (items[2] as FavoriteCountry).name shouldEqual poland
        items[3] shouldEqual footer
    }
}