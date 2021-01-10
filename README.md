

> How to create Menu in Android Kotlin

Kotlin
```kotlin
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.program_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mu_activity_life_cycle -> {
                startActivity(Intent(this, ActivityLifeCycleActivity::class.java))
            }
            R.id.mu_enter_text_program -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        return true
    }
```
XML
```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/mu_activity_life_cycle"
        android:title="@string/activity_life_cycle" />
    <item
        android:id="@+id/mu_enter_text_program"
        android:title="@string/edit_text_to_textview" />
</menu>
```

