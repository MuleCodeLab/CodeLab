package app.storage;

public class CSS {

    public static final String INFO_PAGE = """
            h3 {
                font-family: sans-serif;
                /*margin-bottom: 0.2em;*/
                padding: 0;
            }

            .body {
                font-family: sans-serif;
                font-size: 1.1em;
                white-space: pre-wrap;
                margin-top: 0;
                margin-bottom: 1em;
            }

            .note {
                font-family: sans-serif;
                font-size: 1.1em;
                width: 90%;
                margin-left: auto;
                margin-right: auto;
                margin-bottom: 1em;
                color: #004085;
                background: #cce5ff;
                border: 1px solid #b8daff;
                border-radius: 0.25em;
                padding: 0.3em 0.6em;
            }

            .note::before {
                font-family: sans-serif;
                color: #004085;
                font-size: 1.3em;
                font-weight: bold;
                content: "ⓘ";
                vertical-align: -0.05em;
            }

            img {
                max-width: 80%;
                height: auto;
                border: 1px solid #bbbbbb;
                border-radius: 0.25em;
                display: block;
                margin-left: auto;
                margin-right: auto;
                margin-bottom: 2em;
            }

            .sampleIO_table {
                width: 100%;
                border-collapse:"separate";
                border-spacing: 0.25em 0.5em;
                margin-top: 2em;
            }

            .sampleIO_table th {
                font-family: sans-serif;
                font-size: 1.2em;
                text-align: left;
            }

            .sampleIO_table td {
                vertical-align: top;
                line-height: 1.2em;
                color: black;
                background: #F7F7F7;
                border: 1px solid #bbbbbb;
                border-radius: 0.25em;
                padding: 0.5em 0.5em;
                /*background: #303030;
                color: #ffffff;*/
            }

            .codeBlock {
                width: 100%;
                border-collapse:"separate";
                border-spacing: 0.25em 0.5em;
                margin-top: 2em;
            }

            .codeBlock th {
                font-family: sans-serif;
                font-size: 1.2em;
                text-align: left;
            }

            .codeBlock td {
                vertical-align: top;
                line-height: 1.4em;
                border: 1px solid #bbbbbb;
                border-radius: 0.25em;
                padding: 0.5em 0.5em;
                color: #003311;
                background: #C5EACD;
                border: 1px solid #86CE94;
                /*background: #303030;*/
            }

            code {
                white-space: pre-wrap;
                font-family: monospace;
            }
            """;

}
