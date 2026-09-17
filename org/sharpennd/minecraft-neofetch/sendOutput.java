package org.kwesou.mcNeofetch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class sendOutput implements CommandExecutor {
   StringBuilder neofetchOutput = new StringBuilder();
   Process neofetchProcess;

   public String executeNeoFetchCommand() {
      try {
         try {
            this.neofetchProcess = (new ProcessBuilder(new String[]{"fastfetch", "--pipe", "--logo", "none", "--structure", "title:separator:os:kernel:uptime:terminal:cpu:gpu:memory"})).start();
         } catch (IOException var4) {
            try {
               this.neofetchProcess = (new ProcessBuilder(new String[]{"neofetch", "--stdout", "--disable", "packages", "shell", "theme", "icons"})).start();
            } catch (IOException ex) {
               throw new RuntimeException(ex);
            }
         }

         BufferedReader reader = new BufferedReader(new InputStreamReader(this.neofetchProcess.getInputStream()));
         this.neofetchProcess.waitFor();

         String line;
         while((line = reader.readLine()) != null) {
            this.neofetchOutput.append(line).append("\n");
         }
      } catch (IOException | InterruptedException var5) {
         this.neofetchOutput = new StringBuilder("neofetch failed");
      }

      return String.valueOf(this.neofetchOutput);
   }

   public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
      this.executeNeoFetchCommand();
      commandSender.sendMessage(String.valueOf(this.neofetchOutput));
      return false;
   }
}
