package com.scruzism.plugins

import android.content.Context
import android.net.Uri

import com.aliucord.Http
import com.aliucord.Logger
import com.aliucord.Utils
import com.aliucord.api.CommandsAPI
import com.aliucord.entities.Plugin
import com.aliucord.annotations.AliucordPlugin
import com.discord.api.commands.ApplicationCommandType

import java.io.File
import java.io.FileOutputStream
import java.net.URLEncoder

<<<<<<< HEAD
private fun buildReq(path: String, method: String = "GET"): Http.Request{
    //val base = "https://api.makesweet.com/make"
    val base = "https://mkswt-api-aliucord.herokuapp.com/mkswt"
=======
private fun buildReq(path: String, method: String = "POST"): Http.Request{
    val base = "https://mkswt.herokuapp.com"
>>>>>>> 4fcd64ffebd5d45510fc0a7f42b35e141cb0222f
    return Http.Request(base + path, method)
}

private fun makeTempFile(response: Http.Response, mContext: Context): File {
    val tempFile = File.createTempFile("temp", ".gif", mContext.cacheDir)
    val os = FileOutputStream(tempFile)
    response.pipe(os)
    tempFile.deleteOnExit()
    return tempFile
}

@AliucordPlugin
class Makesweet : Plugin() {

    private var log = Logger("makesweet")

    override fun start(ctx: Context) {

        val args = listOf(
                Utils.createCommandOption(
                        ApplicationCommandType.SUBCOMMAND,
                        "text",
                        subCommandOptions = listOf(
                                Utils.createCommandOption(
                                        ApplicationCommandType.STRING,
                                        "text",
                                        "Enter text",
                                        required = true
                                )
                        )
                ),
                Utils.createCommandOption(
                        ApplicationCommandType.SUBCOMMAND,
                        "image"
                ),
                Utils.createCommandOption(
                        ApplicationCommandType.SUBCOMMAND,
                        "textAndImage",
                        subCommandOptions = listOf(
                                Utils.createCommandOption(
                                        ApplicationCommandType.STRING,
                                        "text",
                                        "Add text and pick an image",
                                        required = true
                                )
                        )
                )
        )

        commands.registerCommand("makesweet", "makesweet API", args) {
            if (it.containsArg("text")) {
                val text = it.getSubCommandArgs("text")?.get("text").toString()
<<<<<<< HEAD
                val resp = buildReq("?text=${URLEncoder.encode(text, "UTF-8")}").execute()
=======
                val resp = buildReq("/text?text=${URLEncoder.encode(text, "UTF-8")}").execute()
>>>>>>> 4fcd64ffebd5d45510fc0a7f42b35e141cb0222f
                val file = makeTempFile(resp, ctx)
                it.addAttachment(Uri.fromFile(file).toString(), "makesweet.gif")
            }

            if (it.containsArg("image")) {
<<<<<<< HEAD
//                val image = try { File(it.attachments[0].data.toString()) } catch (t: Throwable) {
//                    log.error(t)
//                    return@registerCommand CommandsAPI.CommandResult("You have not provided an attachment or an error occurred", null, false)
//                }
//                val resp = buildReq("/heart-locket").executeWithMultipartForm(mapOf("images[]" to image))
//                val file = makeTempFile(resp, ctx)
//                it.attachments.clear()
//                it.addAttachment(Uri.fromFile(file).toString(), "makesweet.gif")
            }

            if (it.containsArg("textAndImage")) {
//                val text = it.getSubCommandArgs("textAndImage")?.get("text").toString()
//                val image = try { File(it.attachments[0].data.toString()) } catch (t: Throwable) {
//                    log.error(t)
//                    return@registerCommand CommandsAPI.CommandResult("You have not provided an attachment or an error occurred", null, false)
//                }
//                val resp = buildReq("/heart-locket?text=${URLEncoder.encode(text, "UTF-8")}").executeWithMultipartForm(mapOf("images[]" to image))
//                val file = makeTempFile(resp, ctx)
//                it.attachments.clear()
//                it.addAttachment(Uri.fromFile(file).toString(), "makesweet.gif")
=======
                val image = try { File(it.attachments[0].data.toString()) } catch (t: Throwable) {
                    log.error(t)
                    return@registerCommand CommandsAPI.CommandResult("You have not provided an attachment or an error occurred", null, false)
                }
                val resp = buildReq("/image").executeWithMultipartForm(mapOf("file" to image))
                val file = makeTempFile(resp, ctx)
                it.attachments.clear()
                it.addAttachment(Uri.fromFile(file).toString(), "makesweet.gif")
            }

            if (it.containsArg("textAndImage")) {
                val text = it.getSubCommandArgs("textAndImage")?.get("text").toString()
                val image = try { File(it.attachments[0].data.toString()) } catch (t: Throwable) {
                    log.error(t)
                    return@registerCommand CommandsAPI.CommandResult("You have not provided an attachment or an error occurred", null, false)
                }
                val resp = buildReq("/image?text=${URLEncoder.encode(text, "UTF-8")}").executeWithMultipartForm(mapOf("file" to image))
                val file = makeTempFile(resp, ctx)
                it.attachments.clear()
                it.addAttachment(Uri.fromFile(file).toString(), "makesweet.gif")
>>>>>>> 4fcd64ffebd5d45510fc0a7f42b35e141cb0222f
            }

            CommandsAPI.CommandResult("")
        }

    }

    override fun stop(ctx: Context) = commands.unregisterAll()

}
