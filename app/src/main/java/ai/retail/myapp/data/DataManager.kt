package ai.retail.myapp.data

import ai.retail.myapp.firebase.references.FirebaseReferences


interface DataManager : DbHelper, PreferenceHelper, FirebaseReferences {
}