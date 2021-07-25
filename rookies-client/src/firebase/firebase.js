import firebase from 'firebase'
import 'firebase/storage'

const firebaseConfig = {
    apiKey: "AIzaSyBAoQEn9mJ5gOr-lueZQQspW7TQPH8cRr4",
    authDomain: "rookies-shop.firebaseapp.com",
    projectId: "rookies-shop",
    storageBucket: "rookies-shop.appspot.com",
    messagingSenderId: "380298361666",
    appId: "1:380298361666:web:052ebe954bd7a6481466db",
    measurementId: "G-ML9DRFG08R"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);
firebase.analytics();

export default firebase;