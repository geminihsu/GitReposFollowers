package com.gemini.githubfollowers.view.avatar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import androidx.annotation.NonNull
import androidx.annotation.Dimension
import androidx.annotation.ColorInt
import androidx.annotation.Nullable
import androidx.annotation.IntDef
import com.gemini.githubfollowers.R
import com.gemini.githubfollowers.constants.SHOW_IMAGE
import com.gemini.githubfollowers.constants.SHOW_INITIAL

import kotlin.annotation.AnnotationRetention
import kotlin.annotation.AnnotationRetention.SOURCE

class AvatarImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    CircleImageView(context, attrs) {

    private val mTextPaint: Paint
    private val mTextBounds: Rect

    private val mBackgroundPaint: Paint
    private val mBackgroundBounds: RectF

    @NonNull
    @get:NonNull
    var initial: String? = null
        private set
    @NonNull
    private var mText: String? = null

    private var mShowState: Int = 0

    var text: String?
        @NonNull
        get() = mText
        set(@Nullable text) {
            mText = text ?: ""
            initial = extractInitial(text)
            updateTextBounds()
            invalidate()
        }

    var state: Int
        @State
        get() = mShowState
        set(@State state) {
            if (state != SHOW_INITIAL && state != SHOW_IMAGE) {
                val msg =
                    "Illegal avatar state value: $state, use either SHOW_INITIAL or SHOW_IMAGE constant"
                throw IllegalArgumentException(msg)
            }
            mShowState = state
            invalidate()
        }

    var textSize: Float
        @Dimension
        get() = mTextPaint.textSize
        set(@Dimension size) {
            mTextPaint.textSize = size
            updateTextBounds()
            invalidate()
        }

    var textColor: Int
        @ColorInt
        get() = mTextPaint.color
        set(@ColorInt color) {
            mTextPaint.color = color
            invalidate()
        }

    var avatarBackgroundColor: Int
        @ColorInt
        get() = mBackgroundPaint.color
        set(@ColorInt color) {
            mBackgroundPaint.color = color
            invalidate()
        }

    @IntDef(SHOW_INITIAL, SHOW_IMAGE)
    @Retention(SOURCE)
    annotation class State

    init {

        var text: String? = DEF_INITIAL
        var textColor = Color.WHITE
        var textSize = DEF_TEXT_SIZE
        var backgroundColor = DEF_BACKGROUND_COLOR
        var showState = DEF_STATE

        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.AvatarImageView, 0, 0)

            text = a.getString(R.styleable.AvatarImageView_text)
            textColor = a.getColor(R.styleable.AvatarImageView_textColor, textColor)
            textSize = a.getDimensionPixelSize(R.styleable.AvatarImageView_textSize, textSize)
            backgroundColor =
                a.getColor(R.styleable.AvatarImageView_avatarBackgroundColor, backgroundColor)
            showState = a.getInt(R.styleable.AvatarImageView_view_state, showState)

            a.recycle()
        }

        mShowState = showState
        mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mTextPaint.textAlign = Paint.Align.CENTER
        mTextPaint.color = textColor
        mTextPaint.textSize = textSize.toFloat()

        mTextBounds = Rect()
        mText = text ?: ""
        initial = extractInitial(text)
        updateTextBounds()

        mBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mBackgroundPaint.color = backgroundColor
        mBackgroundPaint.style = Paint.Style.FILL

        mBackgroundBounds = RectF()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateCircleDrawBounds(mBackgroundBounds)
    }

    override fun onDraw(canvas: Canvas) {
        if (mShowState == SHOW_INITIAL) {
            val textBottom = mBackgroundBounds.centerY() - mTextBounds.exactCenterY()
            canvas.drawOval(mBackgroundBounds, mBackgroundPaint)
            canvas.drawText(initial!!, mBackgroundBounds.centerX(), textBottom, mTextPaint)
            drawStroke(canvas)
            drawHighlight(canvas)
        } else {
            super.onDraw(canvas)
        }
    }

    @NonNull
    private fun extractInitial(@Nullable letter: String?): String {
        return if (letter == null || letter.trim { it <= ' ' }.length <= 0) "?" else letter[0].toString()
    }

    private fun updateTextBounds() {
        mTextPaint.getTextBounds(initial, 0, initial!!.length, mTextBounds)
    }

    companion object {

        private val DEF_INITIAL = "A"
        private val DEF_TEXT_SIZE = 90
        private val DEF_BACKGROUND_COLOR = 0xE53935
        @State
        private val DEF_STATE = SHOW_INITIAL
    }
}
