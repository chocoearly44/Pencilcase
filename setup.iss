#define MyAppName "Pencilcase"
#define MyAppVersion "1.0"
#define MyAppPublisher "chocoearly44"

#define MyAppURL "https://github.com/chocoearly44/pencilcase"
#define MyAppUpdates "https://github.com/chocoearly44/pencilcase/releases"
#define MyAppSupport "https://github.com/chocoearly44/pencilcase/wiki"

#define MyAppBugs "https://github.com/chocoearly44/pencilcase/issues"

; Replace this
#define SetupResources "E:\Dokumenti\IdeaProjects\pencilcase\resources"

[Setup]
AppId={{40CDA22B-29FB-4744-AAD9-F82A4B5F8081}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppSupport}
AppUpdatesURL={#MyAppUpdates}
DefaultDirName={autopf}\{#MyAppName}
DisableDirPage=yes
DefaultGroupName={#MyAppName}
DisableProgramGroupPage=yes
OutputBaseFilename=pencilcase-{#MyAppVersion}-setup
SetupIconFile={#SetupResources}\icon.ico
Compression=lzma
SolidCompression=yes
WizardStyle=modern

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Files]
; Add JAR
Source: "{#SetupResources}\pencilcase.jar"; DestDir: "{app}"; Flags: ignoreversion
; Add Command Files
Source: "{#SetupResources}\commands\clear.bat"; DestDir: "{app}"; Flags: ignoreversion
Source: "{#SetupResources}\commands\dnsf.bat"; DestDir: "{app}"; Flags: ignoreversion
Source: "{#SetupResources}\commands\ls.bat"; DestDir: "{app}"; Flags: ignoreversion
Source: "{#SetupResources}\commands\pgp.bat"; DestDir: "{app}"; Flags: ignoreversion
Source: "{#SetupResources}\commands\quit.bat"; DestDir: "{app}"; Flags: ignoreversion
Source: "{#SetupResources}\commands\whois.bat"; DestDir: "{app}"; Flags: ignoreversion

[Registry]
Root: HKLM; Subkey: "SYSTEM\CurrentControlSet\Control\Session Manager\Environment"; ValueType: expandsz; ValueName: "Path"; ValueData: "{olddata};C:\Program Files (x86)\Pencilcase"; Check: NeedsAddPath('C:\Program Files (x86)\Pencilcase')

[Icons]
Name: "{group}\Pencilcase bugs and ideas"; Filename: "{#MyAppBugs}"

[Code]
function NeedsAddPath(Param: string): boolean;
var
  OrigPath: string;
begin
  if not RegQueryStringValue(HKEY_LOCAL_MACHINE,
    'SYSTEM\CurrentControlSet\Control\Session Manager\Environment',
    'Path', OrigPath)
  then begin
    Result := True;
    exit;
  end;

  Result := Pos(';' + Param + ';', ';' + OrigPath + ';') = 0;
end;

