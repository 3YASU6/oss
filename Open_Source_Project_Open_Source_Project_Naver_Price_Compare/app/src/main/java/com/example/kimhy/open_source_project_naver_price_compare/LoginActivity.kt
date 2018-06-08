package com.example.kimhy.open_source_project_naver_price_compare

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView

import java.util.ArrayList
import android.Manifest.permission.READ_CONTACTS
import android.content.Intent
import android.text.TextUtils

import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.firestore.FirebaseFirestore



   // email/password를 통한 로그인

class LoginActivity : AppCompatActivity(), LoaderCallbacks<Cursor> {

    /** 로그인 task를 추적하고 요구가 있을 경우 cancel할 수 있게 합니다.
     * ログインタスクを追跡して、リクエストがあった場合にキャンセルできるようにします。
     */

    private var mAuthTask: UserLoginTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form. 로그인화면 설정
        populateAutoComplete()
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })


        //로그인 button click시 발생하는 동작을 attemptLogin()에다 기술합니다
        email_sign_in_button.setOnClickListener { attemptLogin() }
    }

    private fun populateAutoComplete() {
        if (!mayRequestContacts()) {
            return
        }

        loaderManager.initLoader(0, null, this)
    }

    // contact 접근 허가 확인
    private fun mayRequestContacts(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(email, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, { requestPermissions(arrayOf(READ_CONTACTS), REQUEST_READ_CONTACTS) })
        } else {
            requestPermissions(arrayOf(READ_CONTACTS), REQUEST_READ_CONTACTS)
        }
        return false
    }

    /**アクセス許可要求が完了したときにコールバックを受信
     * 권한 요청이 완료되면 콜백이 수신
     * Callback received when a permissions request has been completed.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete()
            }
        }
    }


    /**
     * 로그인 양식에 지정된 계정에 로그인 또는 등록을 시도.
     * 양식 오류 (잘못된 이메일, 누락 된 필드 등)가있는 경우 오류가 표시되고
     * 실제 로그인 시도가 이루어지지 않음.
     * ↓↓지금은 개발단계니까 로그인 양식 체크 기능은 comment out 합니다. 개발가 어느정도 종료되면 comment 해제 할 것
     */
    private fun attemptLogin() {
//       if (mAuthTask != null) {
//            return
//        }
//
//        // 오류 Reset
//        email.error = null
//        password.error = null
//
//        /** Store values at the time of the login attempt
//         * 로그인 시도시 값 저장
//         */
//        val emailStr = email.text.toString()
//        val passwordStr = password.text.toString()
//
//        var cancel = false
//        var focusView: View? = null
//
//        /** Check for a valid password, if the user entered one.
//         * 사용자가 암호를 입력 한 경우 올바른 암호인지 확인.
//         */
//        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
//            password.error = getString(R.string.error_invalid_password)
//            focusView = password
//            cancel = true
//        }
//
//        /** Check for a valid email address.
//         * 유효한 이메일 주소인지 확인.
//         */
//        if (TextUtils.isEmpty(emailStr)) {
//            email.error = getString(R.string.error_field_required)
//            focusView = email
//            cancel = true
//        } else if (!isEmailValid(emailStr)) {
//            email.error = getString(R.string.error_invalid_email)
//            focusView = email
//            cancel = true
//        }
//
//        if (cancel) {
//            /** There was an error; don't attempt login and focus the first form field with an error.
//             *오류가있었음; 로그인을 시도하지 말고 오류가있는 첫 번째 양식 필드에 초점을 맞 춥음.
//             */
//            focusView?.requestFocus()
//        } else {
//            /** Show a progress spinner, and kick off a background task to perform the user login attempt.
//             * 진행률을 표시하고 백그라운드 작업을 시작하여 사용자 로그인 시도를 수행
//             */
//            showProgress(true)
//            mAuthTask = UserLoginTask(emailStr, passwordStr)
//            mAuthTask!!.execute(null as Void?)
//        }

        // wishList에 이동
        val mainIntent = Intent(this, ItemListActivity::class.java)
        mainIntent.putExtra("userID","")
        startActivity(mainIntent)

    }

    // 오르바른 E-mail양식인지 확인
    private fun isEmailValid(email: String): Boolean {
        return email.contains("@") //@가 포함돼있는가?
    }

    // password가 오르바른 양식인지 확인
    private fun isPasswordValid(password: String): Boolean {
        // 4자 이상의 길이인가?
        return password.length > 4
    }

    /**
     * Shows the progress UI and hides the login form.
     * 진행 UI를 표시하고 로그인 form을 숨김
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        /** On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow for very easy animations. If available, use these APIs to fade-in the progress spinner.
         * Honeycomb MR2에는 매우 쉬운 애니메이션을 허용하는 ViewPropertyAnimator API가 있다. 가능한 경우이 API를 사용하여 진행률을 페이드인한다.
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            login_form.visibility = if (show) View.GONE else View.VISIBLE
            login_form.animate().setDuration(shortAnimTime).alpha((if (show) 0 else 1).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    login_form.visibility = if (show) View.GONE else View.VISIBLE
                }
            })

            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate().setDuration(shortAnimTime).alpha((if (show) 1 else 0).toFloat()).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    login_progress.visibility = if (show) View.VISIBLE else View.GONE
                }
            })
        } else {
            /** The ViewPropertyAnimator APIs are not available, so simply show and hide the relevant UI components.
             * ViewPropertyAnimator API를 사용할 수 없으므로 관련 UI 구성 요소를 표시하거나 숨김.
             */
            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

    override fun onCreateLoader(i: Int, bundle: Bundle?): Loader<Cursor> {
        return CursorLoader(this,
                /** Retrieve data rows for the device user's 'profile' contact.
                 * 장치 사용자의 '프로파일'연락처에 대한 데이터 행을 검색
                 */
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                /** Select only email addresses.
                 * 이메일 주소 만 선택
                 */
                ContactsContract.Contacts.Data.MIMETYPE + " = ?", arrayOf(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE),

                /** Show primary email addresses first. Note that there won't be a primary email address if the user hasn't specified one.
                 * 기본 이메일 주소를 먼저 표시하십시오. 사용자가 이메일 주소를 지정하지 않은 경우 기본 이메일 주소는 없습니다.
                 */
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC")
    }

    override fun onLoadFinished(cursorLoader: Loader<Cursor>, cursor: Cursor) {
        val emails = ArrayList<String>()
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS))
            cursor.moveToNext()
        }

        addEmailsToAutoComplete(emails)
    }

    override fun onLoaderReset(cursorLoader: Loader<Cursor>) {

    }

    private fun addEmailsToAutoComplete(emailAddressCollection: List<String>) {
        /** Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
         *  AutoCompleteTextView에 dropdown 리스트에 표시 할 내용을 알려주는 어댑터를 만듬.
         */
        val adapter = ArrayAdapter(this@LoginActivity, android.R.layout.simple_dropdown_item_1line, emailAddressCollection)
        email.setAdapter(adapter)
    }

    object ProfileQuery {
        val PROJECTION = arrayOf(ContactsContract.CommonDataKinds.Email.ADDRESS, ContactsContract.CommonDataKinds.Email.IS_PRIMARY)
        val ADDRESS = 0
        val IS_PRIMARY = 1
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate the user.
     * 사용자를 인증하는데 사용되는 비동기 로그인 / 등록 작업
     */
    inner class UserLoginTask internal constructor(private val mEmail: String, private val mPassword: String) : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {
            // TODO 네트워크 서비스에 대한 인증을 시도

            // ↓FireBase 와의 통신...
            try {
                // 네트워크 액세스 시뮬레이션.

            } catch (e: InterruptedException) {
                return false
            }

            return DUMMY_CREDENTIALS.map { it.split(":") }.firstOrNull { it[0] == mEmail }?.let {
                // 계정이 있으면 암호가 일치할때 true을 반환
                it[1] == mPassword
            } ?: true
        }

        // 잘못됀 비밀번호를 입력했을때 경고문 표시
        override fun onPostExecute(success: Boolean?) {
            mAuthTask = null
            showProgress(false)
            if (success!!) {
                finish()
            } else {
                password.error = getString(R.string.error_incorrect_password)
                password.requestFocus()
            }
        }

        override fun onCancelled() {
            mAuthTask = null
            showProgress(false)
        }
    }

    companion object {
        /**
         * Id to identity READ_CONTACTS permission request.
         * ID는 READ_CONTACTS 권한 요청을 식별한다.
         */
        private val REQUEST_READ_CONTACTS = 0

        /**
         * A dummy authentication store containing known user names and passwords.
         * 알려진 사용자 이름과 암호를 포함하는 더미 인증 저장소.
         * TODO : 실제 인증 시스템에 연결 한 후 제거 할 것
         */
        private val DUMMY_CREDENTIALS = arrayOf("foo@example.com:hello", "bar@example.com:world")
    }
}
