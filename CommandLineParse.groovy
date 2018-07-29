class CommandLineParse {
    TypeOfCommand command
    def enterParams

    enum TypeOfCommand{
        EMPTY,SET,RESET,COORDINATES,SHOWALL
    }

    def parse(String[] args) {
        def cli = new CliBuilder(
                usage: 'detectPlace [<options>]'
                ,header: 'Options:'
                , stopAtNonOption: false
        )

        cli.h(longOpt: 'help', required: false, argName: 'help', 'show help')
        cli.S(longOpt: 'set', required: false, argName: 'set', 'Change Settings. Example: -s minRadius=2', args: 2, valueSeparator: '=')
        cli.r(longOpt: 'reset', required: false, argName: 'reset', 'Restore Settings to default')
        cli.C(longOpt: 'coordinates', required: false, argName: 'coordinates', 'Take near places on coordinates. Example: -c 45.454545,35.353535', args: 1)
        cli.a(longOpt: 'showall', required: false, argName: 'all', 'Show all settings')
        def options = cli.parse(args)

        if (!options & args.length > 0) {
            command=TypeOfCommand.EMPTY
            return
        }
        if (options.h) {
            command=TypeOfCommand.EMPTY
            cli.usage()
            return
        }
        if (options.r) {
            command=TypeOfCommand.RESET
            //сбрасываем настройки на стандартные
            return
        }

        if (options.S) {
            //изменяем указанный параметр
            command=TypeOfCommand.SET
            enterParams=options.Ss
            return
        }
        if (options.a) {
            //показываем все настройки программы
            command=TypeOfCommand.SHOWALL
            return
        }
        if (options.C) {
            //были введены координаты
            enterParams=options.Cs
            command=TypeOfCommand.COORDINATES
            return
        }
        cli.usage()
        command=TypeOfCommand.EMPTY

    }
}
